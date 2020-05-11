using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Tendencia
{
    class Grupo
    {
        public List<string> estudiantes { get; set; }
        public List<string> temas { get; set; }

        public Grupo()
        {
            estudiantes = new List<string>();
            temas = new List<string>();
        }

        public void agregarEstudiante(string estudiante)
        {
            estudiantes.Add(estudiante);
        }
        public void agregarTema(string tema)
        {
            temas.Add(tema);
        }
    }
}
