using System;
using System.IO;
using System.Net.Http;
using System.Threading;
using System.Threading.Tasks;
using Google.Protobuf;
using Grpc.Core;
using Grpc.Net.Client;

namespace GrpcImageClient
{
    class Program
    {
        static async Task Main(string[] args)
        {

            MyDataInfo.info();

            using var channel = GrpcChannel.ForAddress("http://10.182.216.154:5000");

            var client = new ImageTransfer.ImageTransferClient(channel);


            Console.WriteLine("Wciśnij jakikolwiek przycisk, żeby przesłać");
            Console.ReadLine();

            var call = client.Transfer();


            // receiving server stream
            var fileName = "../../../ImageFromServer.png";
            Stream fs = File.OpenWrite(fileName);

            int packetNum = 0;

            var readTask = Task.Run(async () =>
            {
                
                await foreach (var response in call.ResponseStream.ReadAllAsync())
                {
                    Console.WriteLine("Odbieranie obrazu z serwera, numer pakietu: " + packetNum);
                    fs.Write(call.ResponseStream.Current.Chunk.ToByteArray());
                    packetNum++;
                }
            });

            

            // sending client stream
            var filePath = "../../../ImageFromClient.png";
            using var fs2 = File.Open(filePath, System.IO.FileMode.Open);

            int bytesRead;
            int fileChunkSize = 1024;

            var buffer = new byte[fileChunkSize];


            int packetNumSend = 0;
            while ((bytesRead = await fs2.ReadAsync(buffer)) > 0)
            {
                Console.WriteLine("Wysyłanie obrazu na serwer, numer pakietu: " + packetNumSend);
                await call.RequestStream.WriteAsync(new RequestChunk { Chunk = ByteString.CopyFrom(buffer[0..bytesRead]) });
                packetNumSend++;
            }

            await call.RequestStream.CompleteAsync();
            await readTask;

        }
    }
}
