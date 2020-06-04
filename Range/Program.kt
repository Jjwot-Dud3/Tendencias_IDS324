
fun main()
{
  
 val a = Range("(2,6)");
 //val x: IntArray = intArrayOf(1, 2, 3)
 //val y: Int = 3;
 //println(firstHalf)
 //val arr = a.get_AllPoints();
 //val x: IntArray = intArrayOf(1, 2, 3)
 //for(element in arr)
 //{
    // println(element)
 //}
 val x = Range("[1,8]");
val arr = a.get_AllPoints();
 for(element in arr)
 {
    println(element)
 }

 println(a.Overlaps_Ranges(x))
}
public class Range(rangeString: String){
    val rangeString = "$rangeString"
    var allPoints: Array<Int>
    var lowerPoint: Int =0 
    var upperPoint: Int= 0
    var error: Boolean = false

    init {
        rangeString.trim();
        
    val splitted= rangeString.split(',')
    if(splitted.size > 1){

        if(splitted[0].contains("("))
        {
            val firstHalf = splitted[0].trim('(');
            val tempNum: Int? = try { firstHalf.toInt();} catch(e: Exception){null};
            if(tempNum ==  null){
                lowerPoint = 0;
                error = true;
                println("Invalid Format")
            }else{
                lowerPoint = tempNum.plus(1);
            }

        }
        
        if (splitted[0].contains("["))
        {
            val firstHalf = splitted[0].trim('[');
            val tempNum: Int? = try { firstHalf.toInt();} catch(e: Exception){null};
            if(tempNum ==  null){
                lowerPoint = 0;
                error = true;
                println("Invalid Format")
            }else{
                lowerPoint = tempNum;
            }

        }
        if(splitted[1].contains(")"))
        {
            val secondHalf = splitted[1].trim(')');
            
            val tempNum = secondHalf.toInt();
            upperPoint = tempNum - 1;
            
        }
        if (splitted[1].contains("]"))
        {
            
            val secondHalf =  splitted[1].trim(']');
            upperPoint = secondHalf.toInt();
            //println(upperPoint);
        }
        if(lowerPoint <= upperPoint){
            val tempArr :ArrayList<Int> = ArrayList()
            val count: Int = 0;
            for(x in lowerPoint .. upperPoint)
            {
                 tempArr.add(x)
                 count.inc();
            }
        
            allPoints = tempArr.toTypedArray();
        }
        else
        {
            val tempArr :ArrayList<Int> = ArrayList()
            allPoints = tempArr.toTypedArray();
            error = true;
            println("Invalid Range");
        }   
    }
    else{
         val tempArr :ArrayList<Int> = ArrayList()
        allPoints = tempArr.toTypedArray();
        error = true;
        println("Invalid Range");
    }
    }
    
    fun get_EndPoints(): IntArray
    {
        if(error)
        {
            println("Something went wrong");
            return IntArray(1){0};
        }
        val endpoint: IntArray = intArrayOf(lowerPoint, upperPoint);
        return endpoint; 
    }
    fun get_AllPoints(): Array<Int>
    {
        if(error)
        {
            val tempArr :ArrayList<Int> = ArrayList()
            println("Something went wrong");
           return tempArr.toTypedArray();
            
        }  
        val tempPoint = allPoints
        return tempPoint;
    }
    fun Contains_inRange(num: Int): Boolean
    {
        if(error)
        {
            println("Something went wrong");
            return false
            
        } 
        for (i in allPoints) 
        {
            if(num == i)
            {
                return true;
            }
        }
        return false;
    }
    fun doesntContains_inRange(num: Int): Boolean
    {
        if(error)
        {
            println("Something went wrong");
            return false
            
        } 
        return !Contains_inRange(num);
    }
    
    fun Contains_inRange(arr: IntArray): Boolean
    {
        if(error)
        {
            println("Something went wrong");
            return false
        } 
        if(arr.size > allPoints.size || arr.size == 0)
        {
            return false;
        }
        for (i in 0 until arr.size) 
        {
            if(!(allPoints.contains(arr[i])))
            {
                return false;
            }
        }
        return true;

    }
    fun Contains_inRange(arr: Array<Int>): Boolean
    {
        if(error)
        {
            println("Something went wrong");
            return false
        } 
        if(arr.size > allPoints.size || arr.size == 0)
        {
            return false;
        }
        for (i in 0 until arr.size) 
        {
            if(!(allPoints.contains(arr[i])))
            {
                return false;
            }
        }
        return true;

    }
    fun doesntContains_inRange(arr: IntArray): Boolean
    {
        if(error)
        {
            println("Something went wrong");
            return false
        } 
        return !Contains_inRange(arr); 
    }
    fun Contains_aRange(r: Range): Boolean
    {
        if(error|| r.error == true)
        {
            println("Something went wrong");
            return false
        } 
       // println(r.get_AllPoints());
        return Contains_inRange(r.get_AllPoints());
    }
    fun doesntContains_aRange(r: Range): Boolean
    {
        if(error|| r.error == true)
        {
            println("Something went wrong");
            return false
        } 
        return !Contains_aRange(r);
    }
    fun Equals_Ranges(r: Range): Boolean
    {
        if(error|| r.error == true)
        {
            println("Something went wrong");
            return false
        } 
        if (this.lowerPoint == r.lowerPoint && this.upperPoint == r.upperPoint)
            {
                return true;
            }
            return false;
    }
    fun notEquals_Ranges(r: Range): Boolean
    {
        if(error|| r.error == true)
        {
            println("Something went wrong");
            return false
        }
        return !Equals_Ranges(r);
    }
    fun Overlaps_Ranges(r: Range): Boolean
    {
       if(error || r.error == true)
        {
            println("Something went wrong");
            return false
        }
        val r1Lower: Int = this.lowerPoint;
        val r2Lower: Int = r.lowerPoint;
            //val condition: Boolean;
            
            if (r2Lower == r1Lower)
            {
                //condition = true;
                return true;
            }
            if (r2Lower < r1Lower)
            {
                return r.Contains_inRange(r1Lower);
            }
            if (r1Lower < r2Lower)
            {
                return  this.Contains_inRange(r2Lower);
            }

            return false;
    }
}