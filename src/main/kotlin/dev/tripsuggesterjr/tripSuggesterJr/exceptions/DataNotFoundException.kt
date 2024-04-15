package dev.tripsuggesterjr.tripSuggesterJr.exceptions

class DataNotFoundException : Exception {

    constructor() : super() {}

    constructor(message: String) : super(message)
}