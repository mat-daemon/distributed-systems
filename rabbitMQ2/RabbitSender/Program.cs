using System;
using System.Text;
using System.Threading;
using RabbitMQ.Client;


Random rnd = new Random(3);

var factory = new ConnectionFactory { HostName = "156.17.141.214", Port = 5672, UserName = "admin", Password = "admin" };

int working_time = 12;
var end_time = DateTime.Now.AddSeconds(working_time);
var current_time = DateTime.Now;

using var connection = factory.CreateConnection();
using var channel = connection.CreateModel();

channel.QueueDeclare(queue: "wojciechmateusz03",
                     durable: false,
                     exclusive: false,
                     autoDelete: false,
                     arguments: null);


MyDataInfo.info();

int iteration_count = 1;
while (current_time < end_time)
{

    Person p = new Person("Mateusz", iteration_count, DateTime.Now);

    publishMessage(channel, p.SerializeToJson());

    int milliseconds = rnd.Next(3000, 4000);

    Thread.Sleep(milliseconds);

    current_time = DateTime.Now;
    iteration_count++;
}


publishMessage(channel, "END");

Console.WriteLine(" Press [enter] to exit.");
Console.ReadLine();


void publishMessage(IModel channel, string message)
{
    var body = Encoding.UTF8.GetBytes(message);

    channel.BasicPublish(exchange: string.Empty,
                         routingKey: "wojciechmateusz03",
                         basicProperties: null,
                         body: body);
    Console.WriteLine($"{message}");

}