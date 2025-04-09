package com.example.movieticketbookingapp

fun main() {

    //Movie List: MovieNumber ->(Movie name, Price)
    val movies = mapOf(
        1 to Pair("Avengers:Endgame", 250),
        2 to Pair("Oppenheimer", 300),
        3 to Pair("SpiderMan:No Way Home", 200),
        4 to Pair("The Dark Knight", 220),
        5 to Pair("Inception", 240)
    )
    //booking cart(movie name, ticket count)
    val bookings = mutableMapOf<String, Int>()

    while (true) {
        println("\n ---MOVIE TICKET BOOKING SYSTEM---")
        println("1.View Movies")
        println("2.Book tickets")
        println("3.View my bookings")
        println("4.Checkout")
        println("5.Exit")
        println("Choose an option (1-5):")

        when (readLine()?.toIntOrNull()) {

            1 -> {
                println("Available Movies:")
                
              movies.forEach{(id,movieData)->
               val(name,price)= movieData
                  println("$id.$name - Rs.$price")

                  }
            }
            2-> {

                print("Enter the movie number you want to book: ")
                val choice = readLine()?.toIntOrNull()

                if (choice!=null && movies.containsKey(choice)){
                    val(movieName) = movies[choice]!!
                    println("How many tickets for '$movieName'?:")
                    val qty = readLine()?.toIntOrNull()
                    if(qty!=null && qty>0){
                        bookings[movieName] = bookings.getOrDefault(movieName,0)+qty
                        println("Booked $qty tickets for $movieName")
                    }else{
                          println("Invalid ticket quantity")
                    }
                }
            }

            3->{
                if(bookings.isEmpty()){
                    println("No bookings yet")
                }else{
                    println("Your bookings")
                    var total = 0
                   bookings.forEach{(movieName, qty)->

                       val price = movies.values.find { it.first==movieName }?.second?:0
                       val subTotal = qty* price
                       println("$movieName * $qty = Rs.$subTotal")
                       total+=subTotal
                   }
                    println("Total : Rs.$total")
                }
            }

            4->{
                if(bookings.isEmpty()){
                    println("Your cart is empty.Book tickets before checkout")
                }else{
                    println("Processing checkout")
                    var total = 0
                    bookings.forEach{(movieName, qty)->
                       val price = movies.values.find { it.first==movieName }?.second?:0
                         total+= qty* price
                    }
                    println("Booking confirmed, Total amount is Rs. $total")
                    bookings.clear()
                }
            }

            5->{
                println("Thank you for using BookMyShow")
                break
            }
            else->
                println("Invalid option, Please try again")
        }
    }
}
