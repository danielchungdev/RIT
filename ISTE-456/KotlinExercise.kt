package com.example.kotlinexercise

import java.util.*

//a)
data class Person(val name: String, val age: Int? = null)
//b)

//d
fun max (a: Int, b: Int){
    if (a >= b){
        println(a)
    }
    else{
        println(b)
    }
}

//e
class Rectangle (val width: Int, val height: Int = 15){
    val isSquare: Boolean
        get(){
            return height == width
        }
}

//f
enum class Color{
    RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET
}

//f.1
fun getMnemonic(color: Color){
    when (color){
        Color.RED -> println("Richard")
        Color.ORANGE -> println("Orlando")
        Color.YELLOW -> println("Years")
        Color.GREEN -> println("Google")
        Color.BLUE -> println("Brother")
        Color.INDIGO -> println("Ignorant")
        Color.VIOLET -> println("Victory")
    }
}



//f.iv
fun getWarmth(color: Color){
    when (color){
        Color.RED, Color.YELLOW, Color.ORANGE -> println("Warm")
        Color.GREEN -> println("Neutral")
        else -> println("Cold")
    }
}

//i
fun String.removeLastChar(): String = this.substring(this.length - 1)

//j
fun printAllCaps(word: String?){
    if (word == null){
        println(null)
    }
    else{
        println(word.uppercase())
    }
}

//k
fun strLenSafe(word: String?){
    if (word == null){
        println(0)
    }
    else{
        println(word.length)
    }
}

fun main(){
    //c
    //c.i
    val list = listOf<Person>(Person("Daniel", 23), Person("Bonnie"), Person("Joe", 44))
    //c.ii
    val oldestPerson = list.maxByOrNull { it.age?:0 }
    //c.iii
    println("The oldest is: " + oldestPerson)
    //d.i
    max(1,2)
    //e.i
    val rectangle = Rectangle(41, 43)
    //e.ii
    println(rectangle.width)
    println(rectangle.height)
    //e.v
    println(rectangle.isSquare)
    //e.vi
    val square = Rectangle(15)
    println(square.isSquare)
    //f.ii
    getMnemonic(Color.BLUE)
    //f.v
    getWarmth(Color.ORANGE)
    //g
    for(i in 100 downTo 1 step 2){
        if (i == 2){
            println(i)
        }
        else {
            print(i)
        }
    }
    //g.i
    println("done")

    //h
    val binaryReps = TreeMap<Char, String>()
    for (c in 'A'..'F'){
        val binary = Integer.toBinaryString(c.toInt())
        binaryReps[c] = binary
    }
    //h.ii
    for (x in binaryReps){
        val key = x.key;
        val value = x.value;
        println("$key == $value")
    }
    //i.i
    val aa = "Mobile App Dev II"
    println(aa.removeLastChar())
    //j.i
    val tempWord = "abc"
    printAllCaps(tempWord)
    printAllCaps(null)
    //k.ii
    strLenSafe(tempWord)
    strLenSafe(null)
}