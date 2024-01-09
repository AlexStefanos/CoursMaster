const fs = require("fs")
const path = require("path")
const input = fs.readFileSync(path.resolve(__dirname, "input.txt"), "utf8").split("\n") //=> liste au lieu d'avoir une longue string

function predefineSet() {
    var count = 0, gameIndex = 0, slicedLine = "", splittedLine = "", slicedSplittedLine = "", nbColor = "", red = 0, green = 0, blue = 0, maxRed = 0, maxBlue = 0, maxGreen = 0, result = 0
    input.forEach((line) => {
        if(gameIndex < 9) {
            slicedLine = line.slice(8)
            splittedLine = slicedLine.split(/[,;]+/)
            splittedLine.forEach((splitted) => {
                if(splitted.charAt(0) == ' ') {
                    slicedSplittedLine = splitted.slice(1)
                } else {
                    slicedSplittedLine = splitted
                }
                if(slicedSplittedLine.includes("red")) {
                    if(slicedSplittedLine.charAt(1) == ' ') {
                        red = parseInt(slicedSplittedLine.charAt(0))
                        maxRed = parseInt(slicedSplittedLine.charAt(0))
                    } else {
                        nbColor = slicedSplittedLine.slice(0, 2)
                        maxRed = parseInt(nbColor)
                    }
                    if(red > maxRed) {
                        maxRed = red
                    }
                }
                if(slicedSplittedLine.includes("green")) {
                    if(slicedSplittedLine.charAt(1) == ' ') {
                        green = parseInt(slicedSplittedLine.charAt(0))
                    } else {
                        nbColor = slicedSplittedLine.slice(0, 2)
                        green = parseInt(nbColor)
                    }
                    if(green > maxGreen) {
                        maxGreen = green
                    }
                }
                if(slicedSplittedLine.includes("blue")) {
                    if(slicedSplittedLine.charAt(1) == ' ') {
                        maxBlue = parseInt(slicedSplittedLine.charAt(0))
                    } else {
                        nbColor = slicedSplittedLine.slice(0, 2)
                        maxBlue = parseInt(nbColor)
                    }
                    if(blue > maxBlue) {
                        maxBlue = blue
                    }
                }
            })
        } 
        else if(gameIndex < 99) {
            slicedLine = line.slice(9)
            splittedLine = slicedLine.split(/[,;]+/)
            splittedLine.forEach((splitted) => {
                if(splitted.charAt(0) == ' ') {
                    slicedSplittedLine = splitted.slice(1)
                } else {
                    slicedSplittedLine = splitted
                }
                if(slicedSplittedLine.includes("red")) {
                    if(slicedSplittedLine.charAt(1) == ' ') {
                        red = parseInt(slicedSplittedLine.charAt(0))
                        maxRed = parseInt(slicedSplittedLine.charAt(0))
                    } else {
                        nbColor = slicedSplittedLine.slice(0, 2)
                        maxRed = parseInt(nbColor)
                    }
                    if(red > maxRed) {
                        maxRed = red
                    }
                }
                if(slicedSplittedLine.includes("green")) {
                    if(slicedSplittedLine.charAt(1) == ' ') {
                        green = parseInt(slicedSplittedLine.charAt(0))
                    } else {
                        nbColor = slicedSplittedLine.slice(0, 2)
                        green = parseInt(nbColor)
                    }
                    if(green > maxGreen) {
                        maxGreen = green
                    }
                }
                if(slicedSplittedLine.includes("blue")) {
                    if(slicedSplittedLine.charAt(1) == ' ') {
                        maxBlue = parseInt(slicedSplittedLine.charAt(0))
                    } else {
                        nbColor = slicedSplittedLine.slice(0, 2)
                        maxBlue = parseInt(nbColor)
                    }
                    if(blue > maxBlue) {
                        maxBlue = blue
                    }
                }
            })
        }
        else {
            slicedLine = line.slice(10)
            splittedLine = slicedLine.split(/[,;]+/)
            splittedLine.forEach((splitted) => {
                if(splitted.charAt(0) == ' ') {
                    slicedSplittedLine = splitted.slice(1)
                } else {
                    slicedSplittedLine = splitted
                }
                if(slicedSplittedLine.includes("red")) {
                    if(slicedSplittedLine.charAt(1) == ' ') {
                        red = parseInt(slicedSplittedLine.charAt(0))
                        maxRed = parseInt(slicedSplittedLine.charAt(0))
                    } else {
                        nbColor = slicedSplittedLine.slice(0, 2)
                        maxRed = parseInt(nbColor)
                    }
                    if(red > maxRed) {
                        maxRed = red
                    }
                }
                if(slicedSplittedLine.includes("green")) {
                    if(slicedSplittedLine.charAt(1) == ' ') {
                        green = parseInt(slicedSplittedLine.charAt(0))
                    } else {
                        nbColor = slicedSplittedLine.slice(0, 2)
                        green = parseInt(nbColor)
                    }
                    if(green > maxGreen) {
                        maxGreen = green
                    }
                }
                if(slicedSplittedLine.includes("blue")) {
                    if(slicedSplittedLine.charAt(1) == ' ') {
                        maxBlue = parseInt(slicedSplittedLine.charAt(0))
                    } else {
                        nbColor = slicedSplittedLine.slice(0, 2)
                        maxBlue = parseInt(nbColor)
                    }
                    if(blue > maxBlue) {
                        maxBlue = blue
                    }
                }
            })
        }
        gameIndex += 1
        if((maxRed < 12) && (maxGreen < 13) && (maxBlue < 14)) {
            console.log("red : " + maxRed + ", green : " + maxGreen + ", blue : " + maxBlue + "      gameIndex : " + gameIndex)
            console.log(slicedLine)
            result += gameIndex
        }
        red = 0
        maxRed = 0
        green = 0
        maxGreen = 0
        blue = 0
        maxBlue = 0
    })
    console.log("result : " + result)
}

console.log(predefineSet())