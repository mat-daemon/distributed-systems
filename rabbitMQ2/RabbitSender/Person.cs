using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using System.Text.Json;

public class Person
{
    public string Name { get; set; }
    public string Time { get; set; }
    public int Nr { get; set; }

    public Person(string name, int nr, DateTime time)
    {
        Name = name;
        Nr = nr;
        Time = time.ToString("HH:mm:ss"); ;
    }

    public string SerializeToJson()
    {
        return JsonSerializer.Serialize(this);
    }

    public static Person DeserializeFromJson(string json)
    {
        return JsonSerializer.Deserialize<Person>(json);
    }
}