using Google.Protobuf;
using Grpc.Core;
using Microsoft.Extensions.Logging;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Threading.Tasks;

namespace GrpcImageServer
{
    public class ImageTransferService : ImageTransfer.ImageTransferBase
    {
        private readonly ILogger<ImageTransferService> _logger;
        public ImageTransferService(ILogger<ImageTransferService> logger)
        {
            _logger = logger;
        }

        public override async Task Transfer(IAsyncStreamReader<RequestChunk> requestStream, IServerStreamWriter<ResponseChunk> responseStream, ServerCallContext context)
        {
            var filePath = "ImageFromServer.png";

            using var fs = File.Open(filePath, System.IO.FileMode.Open);

            int bytesRead;
            int fileChunkSize = 1024;

            var buffer = new byte[fileChunkSize];

            int chunkSentNr = 1;
            while ((bytesRead = await fs.ReadAsync(buffer)) > 0)
            {
                Console.WriteLine("Wysylam paczke nr " + chunkSentNr);
                await responseStream.WriteAsync(new ResponseChunk
                {
                    Chunk = ByteString.CopyFrom(buffer[0..bytesRead])
                });
                chunkSentNr++;
            }
            fs.Close();

            var filePath2 = "ImageFromClient.png";

            var fs2 = File.OpenWrite(filePath2);

            int chunkNr = 1;
            while (await requestStream.MoveNext())
            {
                Console.WriteLine("Odbieram paczke nr " + chunkNr);
                fs2.Write(requestStream.Current.Chunk.ToByteArray());
                chunkNr++;
            }

            fs2.Close();
        }
    }
}
