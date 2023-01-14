# Souvenir Shop - Frontend

This is the frontend of the [Souvenir Shop](https://github.com/PetarRan/cassandra-project) application built using Java Vaadin Flow Framework and it communicates with the backend which is built using Spring Boot and Cassandra as the database. The main focus of the project is to showcase the ability of Cassandra's clustering and partitioning keys in handling large amounts of data.


## Features
- Browse through a wide range of souvenirs available in the shop
- Search for specific items using keywords
- Add items to your cart and proceed to checkout
- Securely handle user authentication and authorization
- Utilize Vaadin's powerful UI components for efficient and interactive user experience

## Getting Started

### Prerequisites
- Java 8 or later
- Vaadin 14 or later

### Installation and Use

1. Clone the repository
2. Make sure you have the backend running, set up a Cassandra cluster and configure the application to connect to it by modifying the application.properties file
3. Build the project using Gradle or Maven `./gradlew build` or `mvn clean install`
4. Run the Spring Boot application `./gradlew bootRun` or `java -jar target/souvenir-shop-frontend-0.0.1-SNAPSHOT.jar`
5. Access the application by navigating to http://localhost:8080 in your browser

## Built With
- [Java Vaadin Flow Framework](https://vaadin.com/docs/v14/flow/) - The web framework used
- [Java](https://www.java.com/) - Programming language used

## Contributing

Please read [CONTRIBUTING.md](https://github.com/PetarRan/cassandra-frontend/blob/main/CONTRIBUTING.md) for details on our code of conduct, and the process for submitting pull requests to us.

## Authors

- **PetarRan** - [PetarRan](https://github.com/PetarRan)

See also the list of [contributors](https://github.com/PetarRan/cassandra-frontend/contributors) who participated in this project.

## License

This project is licensed under the Unlicence License - see the [LICENSE.md](https://github.com/PetarRan/cassandra-frontend/blob/main/LICENSE.md) file for details.

## Acknowledgments

- Hat tip to anyone whose code was used
- Inspiration
- etc
