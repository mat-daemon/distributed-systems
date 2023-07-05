using System.Text;
using RabbitMQ.Client;

var factory = new ConnectionFactory { HostName = "10.108.127.203", Port=5672, UserName="stud", Password="stud"};
using var connection = factory.CreateConnection();
using var channel = connection.CreateModel();

channel.QueueDeclare(queue: "hello",
                     durable: false,
                     exclusive: false,
                     autoDelete: false,
                     arguments: null);

MyDataInfo.info();

for(int i=0; i<5; i++)
{
    Random rnd = new Random();
    int milliseconds = rnd.Next(3000, 4000);

    Thread.Sleep(milliseconds);

    string message = "Mateusz " + i.ToString();
    var body = Encoding.UTF8.GetBytes(message);

    channel.BasicPublish(exchange: string.Empty,
                         routingKey: "hello",
                         basicProperties: null,
                         body: body);
    Console.WriteLine($"{message}");
}


Console.WriteLine(" Press [enter] to exit.");
Console.ReadLine();