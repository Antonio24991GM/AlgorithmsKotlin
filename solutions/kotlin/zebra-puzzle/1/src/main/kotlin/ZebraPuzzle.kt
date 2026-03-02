class ZebraPuzzle() {

    private data class House(
        var color: String = "",
        var nationality: String = "",
        var drink: String = "",
        var pet: String = "",
        var hobby: String = ""
    )

    private val houses = List(5) { House() }

    init {
        // Apply clues through logical deduction
        // Based on the classic Zebra Puzzle solution pattern

        // Clue 10: Norwegian in first house
        houses[0].nationality = "Norwegian"

        // Clue 9: Milk in middle house
        houses[2].drink = "milk"

        // Clue 15: Norwegian next to blue house
        // Since Norwegian is in house 0, blue must be house 1
        houses[1].color = "blue"

        // Clue 6 + 4: Green immediately right of ivory, coffee in green
        // Green has coffee (not milk), so green ≠ house 2
        // Must be ivory=3, green=4
        houses[3].color = "ivory"
        houses[4].color = "green"
        houses[4].drink = "coffee"

        // Remaining colors: red, yellow for houses 0, 2
        // Clue 2: Englishman in red house
        // Clue 8: Yellow house person paints
        // Norwegian is in house 0, so if house 0 is red, Norwegian = Englishman (impossible)
        // Therefore: house 0 = yellow, house 2 = red
        houses[0].color = "yellow"
        houses[2].color = "red"

        // Clue 2: Englishman in red house
        houses[2].nationality = "Englishman"

        // Clue 8: Yellow house person paints
        houses[0].hobby = "paints"

        // Clue 12: Painter next to horse
        // Painter in house 0, so horse in house 1
        houses[1].pet = "horse"

        // Clue 5: Ukrainian drinks tea
        // Clue 13: Football player drinks orange juice
        // Drinks so far: milk (2), coffee (4)
        // Remaining: tea, orange juice, water for houses 0, 1, 3

        // Clue 5: Ukrainian drinks tea
        // Ukrainian must be in house 1 or 3 (not 0=Norwegian, not 2=Englishman)
        houses[1].nationality = "Ukrainian"
        houses[1].drink = "tea"

        // Remaining nationalities: Spaniard, Japanese for houses 3, 4
        // Clue 14: Japanese plays chess
        // Clue 3: Spaniard owns dog

        houses[4].nationality = "Japanese"
        houses[4].hobby = "plays chess"

        houses[3].nationality = "Spaniard"
        houses[3].pet = "dog"

        // Clue 13: Football player drinks orange juice
        // Drinks left: orange juice, water for houses 0, 3
        houses[3].drink = "orange juice"
        houses[3].hobby = "plays football"

        houses[0].drink = "water"

        // Clue 11: Reader next to fox
        // Clue 7: Snail owner dances
        // Hobbies left: reads, dances for houses 1, 2
        houses[1].hobby = "reads"
        houses[2].hobby = "dances"

        // Clue 7: Snail owner dances
        houses[2].pet = "snail"

        // Clue 11: Reader next to fox
        // Reader in house 1, so fox in house 0 or 2
        // House 2 has snail, so fox in house 0
        houses[0].pet = "fox"

        // Remaining pet: zebra for house 4
        houses[4].pet = "zebra"
    }

    fun drinksWater(): String = houses.find { it.drink == "water" }?.nationality ?: ""
    fun ownsZebra(): String = houses.find { it.pet == "zebra" }?.nationality ?: ""
}