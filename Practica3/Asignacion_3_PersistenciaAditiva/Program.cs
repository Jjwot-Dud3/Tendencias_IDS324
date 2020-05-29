using System;
using System.Linq;

namespace Asignacion_3_PersistenciaAditiva
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Ingrese la longitud");
            String result = Console.ReadLine();

            string[] intList = result.Select(q => new string(q, 1)).ToArray();
            int[] myInts = Array.ConvertAll(intList, int.Parse);

            int count = 0;
            while (myInts.Length > 1)
            {
                int sum = 0;
                for (int i = 0; i < myInts.Length; i++)
                {
                    sum += myInts[i];

                }

                string[] arr = Convert.ToString(sum).Select(q => new string(q, 1)).ToArray();
                myInts = Array.ConvertAll(arr, int.Parse);
                count++;
            }
            Console.WriteLine(count);

            Console.ReadKey();
        }
    }
}
