public class Range(rangeString: String){
    val rangeString = "$rangeString"
    var allPoints: IntArray
    var lowerPoint: Int =0 
    var upperPoint: Int= 0

    init {
        rangeString.trim();
        val splitted = rangeString.split(',');
        if(splitted[0].contains("("))
        {
            val firstHalf = splitted[0].trim('(');
            val tempNum = firstHalf.toInt();
            if(tempNum > 0)
            {
                lowerPoint = tempNum - 1;
            }
            else
            {
                lowerPoint = tempNum + 1;
            }
        }
        if (splitted[0].contains("["))
        {
            val firstHalf =  splitted[0].trim('[');
            lowerPoint = firstHalf.toInt();
        }
         if(splitted[1].contains(")"))
        {
            val secondHalf = splitted[0].trim(')');
            val tempNum = secondHalf.toInt();
            if(tempNum > 0)
            {
                upperPoint = tempNum - 1;
            }
            else
            {
                upperPoint = tempNum + 1;
            }
        }
        if (splitted[1].contains("]"))
        {
            val secondHalf =  splitted[0].trim(']');
            upperPoint = secondHalf.toInt();
        }
        val tempArr = IntArray(upperPoint - lowerPoint + 1);
        val count: Int = 0;
        for(x in lowerPoint ..upperPoint)
        {
            tempArr[count] = x;
            count.inc();
        }
        allPoints = tempArr;
    }
}