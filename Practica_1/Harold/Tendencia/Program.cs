using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace Tendencia
{
    class Program
    {
        public enum opcion{
            Estudiantes,
            Temas
        }
        static void Main(string[] args)
        {
            string pathEstudents, pathTemas;
            int tamanoGrupos, temasPorGrupo, cantidadGrupos;

            List<string> estudiantes = new List<string>();
            List<string> temas = new List<string>();
            List<Grupo>  grupos = new List<Grupo>();


            Console.Write("Ingrese el tamaño del los grupos: ");
            tamanoGrupos = int.Parse( Console.ReadLine() );

            Console.Write("Ingrese el path en el que tiene el archivo de Estudiantes: ");
            pathEstudents = Console.ReadLine();

            Console.Write("Ingrese el path en el que tiene el archivo de Temas: ");
            pathTemas = Console.ReadLine();
            Console.Clear();

            if (!File.Exists(pathEstudents))
            {
                Console.WriteLine("El archivo de estudiantes no existe!");
                Console.ReadKey();
            }
            else if (!File.Exists(pathTemas))
            {
                Console.WriteLine("El archivo de temas no existe!");
                Console.ReadKey();
            }
            else
            {
                estudiantes = File.ReadAllLines(@pathEstudents).ToList();
                temas = File.ReadAllLines(@pathTemas).ToList();

                if(estudiantes.Count() >= tamanoGrupos ){
                    cantidadGrupos = estudiantes.Count() / tamanoGrupos;

                    if (temas.Count() >= cantidadGrupos)
                    {
                        temasPorGrupo = temas.Count() / cantidadGrupos;

                        Repartir(estudiantes, opcion.Estudiantes, tamanoGrupos, cantidadGrupos, grupos);
                        Repartir(temas, opcion.Temas, temasPorGrupo, cantidadGrupos, grupos);
                        ImprimirResultados(grupos);
                        Console.ReadKey();
                    }
                    else
                    {
                        Console.WriteLine("Hay mas grupos que temas para repartir!");
                        Console.ReadKey();
                    }
                }
                else
                {
                    Console.WriteLine("No hay suficientes estudiantes para hacer los grupos!");
                    Console.ReadKey();
                }

            }
        }

        public static void Repartir(List<string> lista, opcion opcion, int iteracionPorGrupo, int cantidadGrupos, List<Grupo> grupos)
        {
            Random random = new Random();

            int auxRandom = 0;

            for (int i = 0; i < cantidadGrupos; i++)
            {
                //agrega un grupo a la lista de grupos
                if(opcion == opcion.Estudiantes)
                    grupos.Add(new Grupo());

                for (int j = 0; j < iteracionPorGrupo; j++)
                {
                    //se rellena el grupo correspondiente con estudiantes\temas al azar
                    auxRandom = random.Next(0, lista.Count());
                    if (opcion == opcion.Estudiantes)
                        grupos[i].agregarEstudiante(lista[auxRandom]);

                    if (opcion == opcion.Temas)
                        grupos[i].agregarTema(lista[auxRandom]);

                    lista.RemoveAt(auxRandom);
                }
            }

            //repartir los sobrantes
            if (lista.Count() > 0)
            {
                int grupoRandom = 0;
                List<int> repetidos = new List<int>();
                for (int i = 0; i < lista.Count(); i++)
                {
                    auxRandom = random.Next(0, lista.Count());

                    //para no agregar un estudante extra a un grupo que ya tiene uno
                    do
                    {
                        grupoRandom = random.Next(0, grupos.Count());
                    } while (repetidos.Contains(auxRandom));
                    repetidos.Add(grupoRandom);

                    //se agrega un estudiante/tema extra al azar  a un grupo al azar
                    if (opcion == opcion.Estudiantes)
                        grupos[grupoRandom].agregarEstudiante(lista[auxRandom]);

                    if (opcion == opcion.Temas)
                        grupos[grupoRandom].agregarTema(lista[auxRandom]);

                    lista.RemoveAt(auxRandom);
                }

            }
        }

        public static void ImprimirResultados(List<Grupo> grupos)
        {
            for (int i = 0; i < grupos.Count(); i++)
            {
                Console.WriteLine("Grupo " + (i+1));
                Console.Write("Miembros: ");
                foreach (string estudiante in grupos[i].estudiantes)
                {
                    Console.Write(estudiante+" - ");
                }
                Console.WriteLine();

                Console.Write("Temas: ");
                foreach (string temas in grupos[i].temas)
                {
                    Console.Write(temas + " - ");
                }
                Console.WriteLine("\n\n");
            }
        }
    }
}
