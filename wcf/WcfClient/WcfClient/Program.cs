using System;
using System.Collections.Generic;
using System.Linq;
using System.ServiceModel;
using System.Text;
using System.Threading.Tasks;
using WcfService;

namespace WcfClient
{
    class Program
    {
        static async Task Main(string[] args)
        {
            MyDataInfo.info();

            int result;

            Console.WriteLine("Creating WSHttpBinding_ICalculator client...");
            WcfClient.ServiceReference2.CalculatorClient myClient2 = new WcfClient.ServiceReference2.CalculatorClient("WSHttpBinding_ICalculator1");
            Console.WriteLine("Client created.");
            Console.WriteLine("Program is ready to use.");


            while (true)
            {

                Console.WriteLine();
                Console.WriteLine("Menu\n1. Add\n2. Subtract\n3. Multiply\n4. Divide\n5. Modulo\n6. Biggest prime\n7. Exit\n");
                Console.Write("Choose option: ");
                int option = Convert.ToInt32(Console.ReadLine());

                if (option == 7) break;

                Console.WriteLine();

                int val1 = 0;
                bool validInput = false;

                while (!validInput)
                {
                    Console.Write("Enter val1: ");
                    string input = Console.ReadLine();

                    if (int.TryParse(input, out val1))
                    {
                        validInput = true;
                    }
                    else
                    {
                        Console.WriteLine("Invalid input. Please enter a valid integer.");
                    }
                }

                int val2 = 0;
                bool validInput2 = false;

                while (!validInput2)
                {
                    Console.Write("Enter val2: ");
                    string input = Console.ReadLine();

                    if (int.TryParse(input, out val2))
                    {
                        validInput2 = true;
                    }
                    else
                    {
                        Console.WriteLine("Invalid input. Please enter a valid integer.");
                    }
                }



                Console.WriteLine();


                switch (option)
                {
                    case 1:
                        try
                        {
                            result = myClient2.iAdd(val1, val2);
                            Console.WriteLine("Result: {0}", result);
                        }
                        catch (Exception ex)
                        {
                            Console.WriteLine(ex.Message);
                        }
                        break;
                    case 2:
                        try
                        {
                            result = myClient2.iSub(val1, val2);
                            Console.WriteLine("Result: {0}", result);
                        }
                        catch (Exception ex)
                        {
                            Console.WriteLine(ex.Message);
                        }
                        break;
                    case 3:

                        try
                        {
                            result = myClient2.iMul(val1, val2);
                            Console.WriteLine("Result: {0}", result);
                        }
                        catch (Exception ex)
                        {
                            Console.WriteLine(ex.Message);
                        }

                        break;
                    case 4:
                        try
                        {
                            result = myClient2.iDiv(val1, val2);
                            Console.WriteLine("Result: {0}", result);
                        }
                        catch (Exception ex)
                        {
                            Console.WriteLine(ex.Message);
                        }
                        break;
                    case 5:
                        try
                        {
                            result = myClient2.iMod(val1, val2);
                            Console.WriteLine("Result: {0}", result);
                        }
                        catch (Exception ex)
                        {
                            Console.WriteLine(ex.Message);
                        }
                        break;
                    case 6:
                        //asyncCalled = true;
                        callAsyncPrime(myClient2, val1, val2);
                        break;
                }

            }
            
        }



        static async Task callAsyncPrime(WcfClient.ServiceReference2.CalculatorClient myClient2, int n1, int n2)
        {
            Console.WriteLine("Async method called");
            Console.WriteLine("You can normally use the program.");
            Console.WriteLine("When task is ready you will be informed about the result.");
            Console.WriteLine();

            KeyValuePair<int, int> reply = await myClient2.iBiggestPrimeAsync(n1, n2);

            Console.WriteLine();
            Console.WriteLine("---------------------------------------");
            Console.WriteLine("Asynchronous task is completed");
            Console.WriteLine("The result is {0}", reply);
            Console.WriteLine("Number of primes " + reply.Key);
            Console.WriteLine("Biggest prime " + reply.Value);
            Console.WriteLine("---------------------------------------");

        }
    }


    
}
