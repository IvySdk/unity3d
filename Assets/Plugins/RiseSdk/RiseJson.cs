using System;
using System.Collections;
using System.Collections.Generic;
using System.IO;
using System.Runtime.CompilerServices;
using System.Text;

public static class RiseJson
{
    public static object Deserialize(string json)
    {
        if (json == null)
        {
            return null;
        }
        return Parser.Parse(json);
    }

    public static string Serialize(object obj)
    {
        return Serializer.Serialize(obj);
    }

    private sealed class Parser : IDisposable
    {
        [CompilerGenerated]
        private static Dictionary<string, int> f__switchmap0;
        private StringReader json;
        private const string WHITE_SPACE = " \t\n\r";
        private const string WORD_BREAK = " \t\n\r{}[],:\"";

        private Parser(string jsonString)
        {
            this.json = new StringReader(jsonString);
        }

        public void Dispose()
        {
            this.json.Dispose();
            this.json = null;
        }

        private void EatWhitespace()
        {
            while (" \t\n\r".IndexOf(this.PeekChar) != -1)
            {
                this.json.Read();
                if (this.json.Peek() == -1)
                {
                    break;
                }
            }
        }

        public static object Parse(string jsonString)
        {
            using (RiseJson.Parser parser = new RiseJson.Parser(jsonString))
            {
                return parser.ParseValue();
            }
        }

        private List<object> ParseArray()
        {
            List<object> list = new List<object>();
            this.json.Read();
            bool flag = true;

            while (flag)
            {
                TOKEN nextSymbol = this.NextSymbol;

                switch (nextSymbol)
                {
                    case TOKEN.SQUARED_CLOSE:
                        {
                            flag = false;
                            continue;
                        }
                    case TOKEN.COMMA:
                        {
                            continue;
                        }
                    case TOKEN.NONE:
                        return null;
                }

                object item = this.ParseBySymbol(nextSymbol);
                list.Add(item);
            }
            return list;
        }

        private object ParseBySymbol(TOKEN symbol)
        {
            switch (symbol)
            {
                case TOKEN.CURLY_OPEN:
                    return this.ParseObject();

                case TOKEN.SQUARED_OPEN:
                    return this.ParseArray();

                case TOKEN.STRING:
                    return this.ParseString();

                case TOKEN.NUMBER:
                    return this.ParseNumber();

                case TOKEN.TRUE:
                    return true;

                case TOKEN.FALSE:
                    return false;

                case TOKEN.NULL:
                    return null;
            }
            return null;
        }

        private object ParseNumber()
        {
            double num2;
            string nextWord = this.NextWord;

            if (nextWord.IndexOf('.') == -1)
            {
                long num;
                long.TryParse(nextWord, out num);
                return num;
            }
            double.TryParse(nextWord, out num2);
            return num2;
        }

        private Dictionary<string, object> ParseObject()
        {
            Dictionary<string, object> dictionary = new Dictionary<string, object>();
            this.json.Read();
            while (true)
            {
                TOKEN nextSymbol = this.NextSymbol;

                switch (nextSymbol)
                {
                    case TOKEN.NONE:
                        return null;

                    case TOKEN.CURLY_CLOSE:
                        return dictionary;
                }
                if (nextSymbol != TOKEN.COMMA)
                {
                    string str = this.ParseString();

                    if (str == null)
                    {
                        return null;
                    }
                    if (this.NextSymbol != TOKEN.COLON)
                    {
                        return null;
                    }
                    this.json.Read();
                    dictionary[str] = this.ParseValue();
                }
            }
        }

        private string ParseString()
        {
            StringBuilder builder = new StringBuilder();
            this.json.Read();
            bool flag = true;

            while (flag)
            {
                StringBuilder builder2 = null;
                int num = 0;

                if (this.json.Peek() == -1)
                {
                    flag = false;
                    break;
                }

                char nextChar = this.NextChar;
                char ch2 = nextChar;

                if (ch2 == '"')
                {
                    flag = false;
                    continue;
                }
                if (ch2 != '\\')
                {
                    goto Label_0171;
                }
                if (this.json.Peek() == -1)
                {
                    flag = false;
                    continue;
                }
                nextChar = this.NextChar;

                char ch3 = nextChar;

                switch (ch3)
                {
                    case 'n':
                        {
                            builder.Append('\n');
                            continue;
                        }
                    case 'r':
                        {
                            builder.Append('\r');
                            continue;
                        }
                    case 't':
                        {
                            builder.Append('\t');
                            continue;
                        }
                    case 'u':
                        builder2 = new StringBuilder();
                        num = 0;
                        goto Label_014A;

                    default:
                        {
                            if (((ch3 != '"') && (ch3 != '/')) && (ch3 != '\\'))
                            {
                                if (ch3 == 'b')
                                {
                                    goto Label_00E4;
                                }
                                if (ch3 == 'f')
                                {
                                    goto Label_00F1;
                                }
                            }
                            else
                            {
                                builder.Append(nextChar);
                            }
                            continue;
                        }
                }
            Label_00E4:
                builder.Append('\b');
                continue;
            Label_00F1:
                builder.Append('\f');
                continue;
            Label_0137:
                builder2.Append(this.NextChar);
                num++;
            Label_014A:
                if (num < 4)
                {
                    goto Label_0137;
                }
                builder.Append((char)Convert.ToInt32(builder2.ToString(), 0x10));
                continue;
            Label_0171:
                builder.Append(nextChar);
            }
            return builder.ToString();
        }

        private object ParseValue()
        {
            TOKEN nextSymbol = this.NextSymbol;
            return this.ParseBySymbol(nextSymbol);
        }

        private char NextChar
        {
            get
            {
                return Convert.ToChar(this.json.Read());
            }
        }

        private TOKEN NextSymbol
        {
            get
            {
                if (this.json.Peek() != -1)
                {
                    this.EatWhitespace();
                    switch (this.PeekChar)
                    {
                        case '"':
                            return TOKEN.STRING;

                        case ',':
                            this.json.Read();
                            return TOKEN.COMMA;

                        case '-':
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9':
                            return TOKEN.NUMBER;

                        case ':':
                            return TOKEN.COLON;

                        case '[':
                            return TOKEN.SQUARED_OPEN;

                        case ']':
                            this.json.Read();
                            return TOKEN.SQUARED_CLOSE;

                        case '{':
                            return TOKEN.CURLY_OPEN;

                        case '}':
                            this.json.Read();
                            return TOKEN.CURLY_CLOSE;
                    }
                    string nextWord = this.NextWord;

                    if (nextWord != null)
                    {
                        int num;

                        if (f__switchmap0 == null)
                        {
                            Dictionary<string, int> dictionary = new Dictionary<string, int>(3);
                            dictionary.Add("false", 0);
                            dictionary.Add("true", 1);
                            dictionary.Add("null", 2);
                            f__switchmap0 = dictionary;
                        }
                        if (f__switchmap0.TryGetValue(nextWord, out num))
                        {
                            switch (num)
                            {
                                case 0:
                                    return TOKEN.FALSE;

                                case 1:
                                    return TOKEN.TRUE;

                                case 2:
                                    return TOKEN.NULL;
                            }
                        }
                    }
                }
                return TOKEN.NONE;
            }
        }

        private string NextWord
        {
            get
            {
                StringBuilder builder = new StringBuilder();

                while (" \t\n\r{}[],:\"".IndexOf(this.PeekChar) == -1)
                {
                    builder.Append(this.NextChar);
                    if (this.json.Peek() == -1)
                    {
                        break;
                    }
                }
                return builder.ToString();
            }
        }

        private char PeekChar
        {
            get
            {
                return Convert.ToChar(this.json.Peek());
            }
        }

        private enum TOKEN
        {
            NONE,
            CURLY_OPEN,
            CURLY_CLOSE,
            SQUARED_OPEN,
            SQUARED_CLOSE,
            COLON,
            COMMA,
            STRING,
            NUMBER,
            TRUE,
            FALSE,
            NULL
        }
    }

    private sealed class Serializer
    {
        private StringBuilder builder = new StringBuilder();

        private Serializer()
        {
        }

        public static string Serialize(object obj)
        {
            RiseJson.Serializer serializer = new RiseJson.Serializer();
            serializer.SerializeValue(obj);
            return serializer.builder.ToString();
        }

        private void SerializeArray(IList anArray)
        {
            this.builder.Append('[');

            bool flag = true;
            IEnumerator enumerator = anArray.GetEnumerator();

            try
            {
                while (enumerator.MoveNext())
                {
                    object current = enumerator.Current;

                    if (!flag)
                    {
                        this.builder.Append(',');
                    }
                    this.SerializeValue(current);
                    flag = false;
                }
            }
            finally
            {
                IDisposable disposable = enumerator as IDisposable;
                if (disposable != null)
                    disposable.Dispose();
            }
            this.builder.Append(']');
        }

        private void SerializeObject(IDictionary obj)
        {
            bool flag = true;
            this.builder.Append('{');
            IEnumerator enumerator = obj.Keys.GetEnumerator();

            try
            {
                while (enumerator.MoveNext())
                {
                    object current = enumerator.Current;

                    if (!flag)
                    {
                        this.builder.Append(',');
                    }
                    this.SerializeString(current.ToString());
                    this.builder.Append(':');
                    this.SerializeValue(obj[current]);
                    flag = false;
                }
            }
            finally
            {
                IDisposable disposable = enumerator as IDisposable;
                if (disposable != null)
                    disposable.Dispose();
            }
            this.builder.Append('}');
        }

        private void SerializeOther(object value)
        {
            if ((((value is float) || (value is int)) || ((value is uint) || (value is long))) || ((((value is double) || (value is sbyte)) || ((value is byte) || (value is short))) || (((value is ushort) || (value is ulong)) || (value is decimal))))
            {
                this.builder.Append(value.ToString());
            }
            else
            {
                this.SerializeString(value.ToString());
            }
        }

        private void SerializeString(string str)
        {
            this.builder.Append('"');
            foreach (char ch in str.ToCharArray())
            {
                switch (ch)
                {
                    case '\b':
                        this.builder.Append(@"\b");
                        break;

                    case '\t':
                        this.builder.Append(@"\t");
                        break;

                    case '\n':
                        this.builder.Append(@"\n");
                        break;

                    case '\f':
                        this.builder.Append(@"\f");
                        break;

                    case '\r':
                        this.builder.Append(@"\r");
                        break;

                    case '"':
                        this.builder.Append("\\\"");
                        break;

                    case '\\':
                        this.builder.Append(@"\\");
                        break;

                    default:
                        {
                            int num2 = Convert.ToInt32(ch);

                            if ((num2 >= 0x20) && (num2 <= 0x7e))
                            {
                                this.builder.Append(ch);
                            }
                            else
                            {
                                this.builder.Append(@"\u" + Convert.ToString(num2, 0x10).PadLeft(4, '0'));
                            }
                            break;
                        }
                }
            }
            this.builder.Append('"');
        }

        private void SerializeValue(object value)
        {
            if (value == null)
            {
                this.builder.Append("null");
            }
            else
            {
                string str = value as string;

                if (str != null)
                {
                    this.SerializeString(str);
                }
                else if (value is bool)
                {
                    this.builder.Append(value.ToString().ToLower());
                }
                else
                {
                    IList anArray = value as IList;

                    if (anArray != null)
                    {
                        this.SerializeArray(anArray);
                    }
                    else
                    {
                        IDictionary dictionary = value as IDictionary;

                        if (dictionary != null)
                        {
                            this.SerializeObject(dictionary);
                        }
                        else if (value is char)
                        {
                            this.SerializeString(value.ToString());
                        }
                        else
                        {
                            this.SerializeOther(value);
                        }
                    }
                }
            }
        }
    }
}
