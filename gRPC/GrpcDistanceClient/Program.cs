using System;
using System.Threading.Tasks;
using Grpc.Net.Client;
using GrpcDistanceCalculator;


public class Program
{

    public static void Main(string[] args)
    {
        MyDataInfo.info();

        using var channel = GrpcChannel.ForAddress("http://10.182.216.154:5000");
        var client = new DistanceCalculator.DistanceCalculatorClient(channel);

        string menu = "Menu\n" +
            "1. Distance between two cities\n" +
            "2. Distance between three cities\n" +
            "3. End";

        bool endLoop = false;

        while (!endLoop)
        {
            Console.WriteLine(menu);
            string option = Console.ReadLine();

            Point p1;
            Point p2;
            Point p3;
            Request request;

            switch (option)
            {
                case "1":
                    p1 = takeParameters();
                    p2 = takeParameters();
                    request = new Request();
                    request.P1 = p1;
                    request.P2 = p2;
                    request.P3 = null;

                    var reply1 = client.distance(request);
                    Console.WriteLine("Odległość między miastami " + p1.Name + " a " + p2.Name + " " + reply1.Result);
                    Console.WriteLine(reply1.Result);
                    break;
                case "2":
                    p1 = takeParameters();
                    p2 = takeParameters();
                    p3 = takeParameters();
                    request = new Request();
                    request.P1 = p1;
                    request.P2 = p2;
                    request.P3 = p3;

                    var reply2 = client.distance(request); 
                    Console.WriteLine("Odległość między miastami " + p1.Name + " a " + p2.Name + " przez " + p3.Name + " " + reply2.Result);
                    break;
                case "3":
                    endLoop = true;
                    break;
                default:
                    Console.WriteLine(menu);
                    break;
            }
        }
    }

    public static Point takeParameters()
    {
        Point p = new Point();

        Console.Write("Enter city name: ");
        string cityName = Console.ReadLine();
        Console.Write("Enter longitude: ");
        string longitudeS = Console.ReadLine();
        double longitude = Double.Parse(longitudeS);
        Console.Write("Enter latitude: ");
        string latitudeS = Console.ReadLine();
        double latitude = Double.Parse(latitudeS);

        p.Name = cityName;
        p.Latitude = latitude;
        p.Longitude = longitude;

        return p;
    }

}

