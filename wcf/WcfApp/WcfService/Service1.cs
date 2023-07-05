using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;

namespace WcfService
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the class name "Service1" in both code and config file together.
    [ServiceBehavior(InstanceContextMode = InstanceContextMode.Single,
        ConcurrencyMode = ConcurrencyMode.Multiple,
        IncludeExceptionDetailInFaults = true)]

    //Dla wartości poza zakresem: -2147483648 do 2147483647
    public class MyCalculator : ICalculator
    {
        public int iAdd(int val1, int val2)
        {
            
            checked
            {
                Console.WriteLine("Dodawanie liczb: " + val1 + " oraz " + val2);
                return val1 + val2;
            }

        }

        public int iSub(int val1, int val2)
        {
            checked
            {
                Console.WriteLine("Odejmowanie liczb: " + val1 + " oraz " + val2);
                return val1 - val2;
            }

        }

        public int iMul(int val1, int val2)
        {
            checked {
                Console.WriteLine("Mnożenie liczb: " + val1 + " oraz " + val2);
                return val1 * val2;
            }
        }

        public int iDiv(int val1, int val2)
        {
            try
            {
                checked {
                    Console.WriteLine("Dzielenie liczb: " + val1 + " oraz " + val2);
                    return val1 / val2;
                }
            }
            catch(Exception ex)
            {
                throw new FaultException(ex.Message);
            }
        }

        public int iMod(int val1, int val2)
        {
            checked {
                Console.WriteLine("Modulo liczb: " + val1 + " oraz " + val2);
                return val1 % val2;
            }
        }

        public KeyValuePair<int, int> iBiggestPrime(int l1, int l2)
        {
            checked {
                Console.WriteLine("Szukanie ilości liczb pierwszych oraz największej liczby pierwszej z liczb: " + l1 + " oraz " + l2);

                var prime = new bool[l2 + 1];
                for (var i = 0; i < prime.Length; i++)
                    prime[i] = true;

                prime[0] = false;
                prime[1] = false;

                for (var p = 2; p * p <= l2; p++)
                {
                    if (prime[p])
                    {
                        for (var i = p * p; i <= l2; i += p)
                            prime[i] = false;
                    }
                }

                var count = 0;
                var max = -1;
                for (var p = l1; p <= l2; p++)
                {
                    if (prime[p])
                    {
                        count++;
                        max = p;
                    }
                }

                Console.WriteLine("Znaleziono ilość liczb pierwszych oraz największej liczby pierwszej z liczb: " + l1 + " oraz " + l2 + ", ilosc liczb pierwszych: " + count + ",  liczba pierwsza " + max);
                return new KeyValuePair<int, int>(count, max);
            }

        }

/*        private bool IsPrime(int number)
        {
            if (number <= 1) return false;
            if (number == 2) return true;
            if (number % 2 == 0) return false;

            var boundary = (int)Math.Floor(Math.Sqrt(number));

            for (int i = 3; i <= boundary; i += 2)
                if (number % i == 0)
                    return false;

            return true;
        }


        public (int, int) Primes(int l1, int l2)
        {
            var prime = new bool[l2 + 1];
            for (var i = 0; i < prime.Length; i++)
                prime[i] = true;

            prime[0] = false;
            prime[1] = false;

            for (var p = 2; p * p <= l2; p++)
            {
                if (prime[p])
                {
                    for (var i = p * p; i <= l2; i += p)
                        prime[i] = false;
                }
            }

            var count = 0;
            var max = -1;
            for (var p = l1; p <= l2; p++)
            {
                if (prime[p])
                {
                    count++;
                    max = p;
                }
            }
            return (count, max);
        }*/
    }
}
