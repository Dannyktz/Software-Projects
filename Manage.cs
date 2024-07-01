using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

public class Shoe
{
    public string Country { get; set; }
    public string Code { get; set; }
    public string Product { get; set; }
    public int Cost { get; set; }
    public int Quantity { get; set; }
    public int Value { get; set; }
    public string Info { get; set; }

    public Shoe(string info)
    {
        Info = info;
        ReadData();
        ValuePerItem();
    }

    public void ReadData()
    {
        try
        {
            var input = Info.Trim();
            var parts = input.Split(',');

            Country = parts[0];
            Code = parts[1];
            Product = parts[2];
            Cost = int.Parse(parts[3]);
            Quantity = int.Parse(parts[4]);
        }
        catch (Exception)
        {
            Console.WriteLine("An error has occurred");
        }
    }

    public void ValuePerItem()
    {
        Value = Cost * Quantity;
    }

    public void Update()
    {
        Info = $"{Country},{Code},{Product},{Cost},{Quantity}\n";
    }
}

class Program
{
    static List<Shoe> list = new List<Shoe>();

    static void FileList()
    {
        using (var reader = new StreamReader("inventory.txt"))
        {
            reader.ReadLine(); // Skip header
            string line;
            while ((line = reader.ReadLine()) != null)
            {
                var newShoe = new Shoe(line);
                list.Add(newShoe);
            }
        }
    }

    static void FileUpdate()
    {
        using (var writer = new StreamWriter("inventory.txt"))
        {
            writer.WriteLine("Country,Code,Product,Cost,Quantity");
            foreach (var shoe in list)
            {
                writer.Write(shoe.Info);
            }
        }
    }

    static int LowestQ()
    {
        var qList = list.Select(shoe => shoe.Quantity).ToList();
        return qList.IndexOf(qList.Min());
    }

    static int HighestQ()
    {
        var qList = list.Select(shoe => shoe.Quantity).ToList();
        return qList.IndexOf(qList.Max());
    }

    static string SearchC(string code)
    {
        var shoe = list.FirstOrDefault(s => s.Code == code);
        return shoe != null ? shoe.Info : "Not found";
    }

    static void Main()
    {
        FileList();

        while (true)
        {
            try
            {
                Console.WriteLine("\t1: Search product\n\t2: Product with the lowest quantity\n\t3: Product with the highest quantity\n\t4: Represent all the data in table\n");
                var value = int.Parse(Console.ReadLine());

                if (value == 1)
                {
                    Console.Write("Please enter product code: ");
                    var name = Console.ReadLine();
                    Console.WriteLine(SearchC(name));
                }
                else if (value == 2)
                {
                    var index = LowestQ();
                    Console.WriteLine(list[index].Info);

                    while (true)
                    {
                        try
                        {
                            Console.Write("Please enter amount you would like to restock to: ");
                            var newValue = int.Parse(Console.ReadLine());
                            list[index].Quantity = newValue;
                            list[index].Update();
                            Console.WriteLine(list[index].Info);
                            break;
                        }
                        catch (FormatException)
                        {
                            Console.WriteLine("Value entered is incorrect");
                        }
                    }
                }
                else if (value == 3)
                {
                    var index = HighestQ();
                    Console.WriteLine(list[index].Info);

                    while (true)
                    {
                        try
                        {
                            Console.Write("Please enter the sale price of the product: ");
                            var newCost = int.Parse(Console.ReadLine());
                            list[index].Cost = newCost;
                            if (!list[index].Product.Contains("(S)"))
                            {
                                list[index].Product += "(S)";
                            }
                            list[index].Update();
                            Console.WriteLine(list[index].Info);
                            break;
                        }
                        catch (FormatException)
                        {
                            Console.WriteLine("Value entered is invalid");
                        }
                    }
                }
                else if (value == 4)
                {
                    var header = new List<string> { "Country", "Code", "Product", "Cost", "Quantity", "Value" };
                    var table = new List<List<string>>();
                    var sale = new List<List<string>>();
                    int totalValue = 0;

                    foreach (var shoe in list)
                    {
                        table.Add(new List<string> { shoe.Country, shoe.Code, shoe.Product, $"R{shoe.Cost}", shoe.Quantity.ToString(), $"R{shoe.Value}" });
                        totalValue += shoe.Value;

                        if (shoe.Product.Contains("(S)"))
                        {
                            sale.Add(new List<string> { shoe.Country, shoe.Code, shoe.Product, $"R{shoe.Cost}", shoe.Quantity.ToString(), $"R{shoe.Value}" });
                        }
                    }

                    table.Add(new List<string> { "TOTAL:", "", "", "", "", $"R{totalValue}" });
                    table.Add(new List<string> { "Items for sale" });
                    table.AddRange(sale);

                    Console.WriteLine(Tabulate(table, header));
                }
                else
                {
                    Console.WriteLine("That is not one of the options!");
                }

                break;
            }
            catch (FormatException)
            {
                Console.WriteLine("Input invalid");
            }
        }

        FileUpdate();
    }

    static string Tabulate(List<List<string>> table, List<string> header)
    {
        var result = new StringWriter();

        for (int i = 0; i < header.Count; i++)
        {
            result.Write(header[i].PadRight(15));
        }
        result.WriteLine();

        foreach (var row in table)
        {
            for (int i = 0; i < row.Count; i++)
            {
                result.Write(row[i].PadRight(15));
            }
            result.WriteLine();
        }

        return result.ToString();
    }
}
