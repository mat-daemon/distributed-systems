using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.NetworkInformation;
using System.Net.Sockets;
using System.Threading.Tasks;

namespace WcfServiceHost
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
            Console.WriteLine("Wojciech Begierski 260415");
            Console.WriteLine(DateTime.Now.ToString("dd-MMM-yyyy, HHmmss"));
            Console.WriteLine(Environment.Version.ToString());
            Console.WriteLine(Environment.UserName);
            Console.WriteLine(Environment.OSVersion);
            string hostName = Dns.GetHostName();
            NetworkInterface[] nics = NetworkInterface.GetAllNetworkInterfaces();
            foreach (NetworkInterface adapter in nics)
            {
                if (adapter.NetworkInterfaceType == NetworkInterfaceType.Wireless80211 && adapter.OperationalStatus == OperationalStatus.Up)
                {
                    IPInterfaceProperties properties = adapter.GetIPProperties();
                    foreach (IPAddressInformation unicast in properties.UnicastAddresses)
                    {
                        if (unicast.Address.AddressFamily == System.Net.Sockets.AddressFamily.InterNetwork)
                        {
                            Console.WriteLine("Your IP address: " + unicast.Address);
                        }
                    }
                }
            }
        }

    }
}