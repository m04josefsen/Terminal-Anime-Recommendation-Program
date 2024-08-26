# Terminal Anime Recommendation Program

## Project Description

The **Terminal Anime Recommendation Program** is a Java-based application designed to provide personalized anime recommendations directly from the terminal. It leverages the MyAnimeList (MAL) API to analyze your watched anime list and generate tailored suggestions based on genres and user ratings. The application features two distinct recommendation algorithms:

1. **MyAnimeList Recommendations**: This algorithm fetches recommendations from MyAnimeList for each anime you've watched. It ranks these recommendations based on combined user scores and the relevance of each suggestion.
   
2. **Underrated Genre Recommendations**: This algorithm identifies your top three favorite genres based on your watched list and provides recommendations for lesser-known animes in these genres, with an emphasis on titles that have not gained widespread popularity.

## Features

- **Custom Recommendations**: Provides personalized anime suggestions based on your viewing history.
- **Two Algorithms**: Offers different perspectives on what to watch next by utilizing both mainstream and underrated anime recommendation methods.
- **Real-time Fetching**: Fetches the latest data from MyAnimeList using the Jikan API.
- **Genre Analysis**: Identifies and ranks your favorite genres based on your past anime ratings.
- **Randomized Recommendations**: Shuffles the list of recommended animes to introduce variety.

## Technologies Used

- **Java**: The primary programming language used for this project.
- **Jikan API**: An unofficial MyAnimeList API utilized for fetching anime data and recommendations.
- **Jackson**: A library for parsing JSON data retrieved from the API.
- **Java HTTP Client**: For making HTTP requests to the Jikan API.
- **SLF4J/Logback**: Used for logging and handling potential errors.

## Video Demo

A video demonstrating the Terminal Anime Recommendation Program in action is available.

[![Terminal Anime Recommendation Program - Preview](https://img.youtube.com/vi/aGnMzNyztiU/0.jpg)](https://www.youtube.com/watch?v=aGnMzNyztiU)

---

*README created by ChatGPT*
