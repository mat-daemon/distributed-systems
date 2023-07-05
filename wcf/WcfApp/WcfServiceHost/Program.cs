using System;
using System.Collections.Generic;
using System.Linq;
using System.ServiceModel;
using System.ServiceModel.Description;
using System.Text;
using System.Threading.Tasks;
using WcfService;

namespace WcfServiceHost
{
    class Program
    {
        static void Main(string[] args)
        {
            MyDataInfo.info();

            Uri baseAddress = new Uri("http://10.182.101.87:1000/MyCalculator");
            ServiceHost myHost = new ServiceHost(typeof(MyCalculator), baseAddress);

            BasicHttpBinding myBinding = new BasicHttpBinding();
            ServiceEndpoint endpoint1 = myHost.AddServiceEndpoint(typeof(ICalculator), myBinding, "endpoint1");


            ServiceMetadataBehavior smb = new ServiceMetadataBehavior();
            smb.HttpGetEnabled = true;
            myHost.Description.Behaviors.Add(smb);


            WSHttpBinding binding2 = new WSHttpBinding();
            binding2.Security.Mode = SecurityMode.None;
            ServiceEndpoint endpoint2 = myHost.AddServiceEndpoint(typeof(ICalculator), binding2, "endpoint2");


            Console.WriteLine("\n---> Endpoints:"); 
            Console.WriteLine("\nService endpoint {0}:", endpoint1.Name); 
            Console.WriteLine("Binding: {0}", endpoint1.Binding.ToString()); 
            Console.WriteLine("ListenUri: {0}", endpoint1.ListenUri.ToString());

            Console.WriteLine("\n---> Endpoints:");
            Console.WriteLine("\nService endpoint {0}:", endpoint2.Name);
            Console.WriteLine("Binding: {0}", endpoint2.Binding.ToString());
            Console.WriteLine("ListenUri: {0}", endpoint2.ListenUri.ToString());

            try
            {
                myHost.Open();
                Console.WriteLine("Service has started and running");
                Console.ReadLine();
                myHost.Close();
            }
            catch(CommunicationException ce)
            {
                Console.WriteLine("Exception occured.");
                myHost.Abort();
            }
                
        }
    }
}
