using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Threading.Tasks;

namespace GrpcDistanceServer
{
    public class MyDataInfo
    {
        public static void info()
        {
            //imie nazwisko indeks
            //data dd-mmm-rr, hhmmss
            //c# version
            //user name
            //system name
            //ip address

            Console.WriteLine("Mateusz Smigielski 260457");
            Console.WriteLine(DateTime.Now.ToString("dd-MMM-yyyy, HHmmss"));
            Console.WriteLine(Environment.Version.ToString());
            Console.WriteLine(Environment.UserName);
            Console.WriteLine(Environment.OSVersion);
            string hostName = Dns.GetHostName();
            Console.WriteLine(Dns.GetHostByName(hostName).AddressList[0].ToString());
        }
    }
}
