import java.security.InvalidParameterException


fun main() {
    //human test eliminateRepetitions
    //println(eliminateRepetitions(listOf(1,2,3,-1,0,2,2,55,55,7,9,10,3,0,-1)))

    //human test prepareList
    //println(prepareList(listOf(1,2,3,-1,0,2,2,55,55,7,9,10,3,0,-1)))

    //human test getListIncrementValue
    //println(getListIncrementValue(listOf(1, 4, 7, 16, 22)))
    //println(getListIncrementValue(listOf(0, 3,  9, 12, 15, 30)))

    //human test getMissedElements
    //println(getMissedElements(0, 10, 2))
    //println(getMissedElements(0, 9, 3))

    //human test getSolution
    //getSolution(listOf(1, 4, 1, 7, 16, 4, 22))
    //getSolution(listOf(61,21, 13, 9, 7, 5, 3, 1))
    //getSolution(listOf(590,210, 170,150, 110, 90, 70, 50, 30, 10))
    //getSolution(listOf(100,22, 19,16, 13, 90, 70, 50, 30, 1))

    getSolution(listOf(1, 4, 1, 7, 16, 4, 22))
}

fun getSolution(list: List<Int>): List<Int> {
    val sortedList = prepareList(list)
    val sequenceIncrementationValue = getListIncrementationValue(sortedList)
    val solution = ArrayList<Int>()
    println("The Missing Element(s) in the Sequence{$sortedList} With start:{${sortedList.first()}}, end:{${sortedList.last()}} and increment:{$sequenceIncrementationValue}")
    for (i in (0..sortedList.size - 2)) {
        val element = sortedList[i]
        val nextElement = sortedList[i + 1]
        val currentDifference = nextElement - element

        if (currentDifference > sequenceIncrementationValue) {
            val missedElements =
                getMissedElements(element,nextElement, sequenceIncrementationValue)

            solution.addAll(missedElements)
        }
    }
    println("Is/Are :$solution")
    return solution
}

fun getMissedElements(firstElement: Int, lastElement: Int, gap: Int): List<Int> {
    //the difference must be multiplier of the (gap or incrementation) value
    val distanceBetweenTheElements = lastElement - firstElement
    if(distanceBetweenTheElements%gap !=0){
        throw InvalidParameterException("THE GIVEN SEQUENCE IS INVALID SOME OF IT'S ELEMENTS ARE WRONG MIGHT BE $firstElement AND/OR $lastElement")
    }
    val missedElementsCount = (distanceBetweenTheElements / gap) - 1
    //so add the missing elements till reach the next element in the sorted array
    //for example 0,10 and the incrementation value is 2
    //we will need to add (10-0/2) - 1   so four  elements till reach 10
    // 2,4,6,8 so (0 + 1 * 2, 0 + 2 * 2, 0 + 3 * 2, 0 + 4 * 2)
    val elements = ArrayList<Int>()
    for (i in 1..missedElementsCount) {
        elements.add(firstElement + i * gap)
    }
    return elements
}

fun getListIncrementationValue(list: List<Int>): Int {
    var incrementationValue = Int.MAX_VALUE
    for (i in (0..list.size - 2)) {
        val currentDifference = list[i + 1] - list[i]
        if (incrementationValue > currentDifference) {
            incrementationValue = currentDifference
        }
    }
    return incrementationValue
}

fun prepareList(list: List<Int>): List<Int> {
    val readyList = eliminateRepetitions(list)
    return readyList.sorted()
}

fun eliminateRepetitions(list: List<Int>): List<Int> {
    var newList = ArrayList<Int>()
    list.forEach { element ->
        if (newList.indexOf(element) == -1) {
            newList.add(element)
        }
    }
    return newList
}