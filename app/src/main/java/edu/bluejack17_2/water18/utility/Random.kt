package edu.bluejack17_2.water18.utility

import java.util.concurrent.ThreadLocalRandom

fun ClosedRange<Int>.random() =
    ThreadLocalRandom.current().nextInt((endInclusive + 1) - start) +  start