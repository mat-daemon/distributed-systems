using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Grpc.Core;
using GrpcDistanceCalculator;

public class DistanceCalculatorService : DistanceCalculator.DistanceCalculatorBase
{
    public override Task<Response> distance(Request request, ServerCallContext context)
    {
        double result;

        if (request.P3 != null) result = distanceBetweenThreePoints(request.P1, request.P2, request.P3);
        else
        {
            result = haversine(request.P1, request.P2);
        }
        return Task.FromResult(new Response { Result = result});
    }

    public double haversine(Point a, Point b)
    {
        const double r = 6378.100; // kilometers

        double lat1 = degreesToRadians(a.Latitude);
        double lon1 = degreesToRadians(a.Longitude);

        double lat2 = degreesToRadians(b.Latitude);
        double lon2 = degreesToRadians(b.Longitude);

        var sdlat = Math.Sin((lat2 - lat1) / 2);
        var sdlon = Math.Sin((lon2 - lon1) / 2);
        var q = sdlat * sdlat + Math.Cos(lat1) * Math.Cos(lat2) * sdlon * sdlon;
        var d = 2 * r * Math.Asin(Math.Sqrt(q));

        return d;
    }

    public double distanceBetweenThreePoints(Point a, Point b, Point c)
    {
        return haversine(a, b) + haversine(b, c);
    }

    public double degreesToRadians(double degree)
    {
        return (Math.PI / 180) * degree;
    }
}
