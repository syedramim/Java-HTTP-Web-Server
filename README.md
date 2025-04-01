# Java HTTP Web Server

## Overview

This project is a lightweight, multi-threaded HTTP web server written in Java. It handles basic HTTP 1.0 requests, serves static content (HTML, images), and returns custom error responses (400 and 404) when applicable. The project demonstrates foundational concepts in computer networking, including request parsing, socket communication, and response construction.

## Features

- Parses HTTP GET requests and serves static files
- Handles malformed requests with a 400 Bad Request page
- Handles missing resources with a 404 Not Found page
- Serves `.html`, `.gif`, and other common file types
- Basic concurrent handling of multiple connections using threads

## Project Structure

```
a5-sramim-main/
├── .gitignore
├── Makefile
├── README.md
├── content/
│   ├── index.html
│   ├── test.html
│   └── parrot.gif
├── errors/
│   ├── 400.html
│   └── 404.html
└── http/
    ├── HTTPServer.java
    ├── HTTPConnection.java
    ├── HTTPRequest.java
    └── HTTPResponse.java
```

## How to Compile and Run

### Compile

```bash
make
```

### Run the Server

```bash
java http.HTTPServer <port>
```

Example:
```bash
java http.HTTPServer 8080
```

### Access in Browser

Visit: `http://localhost:8080/`  
Try different paths like `/test.html`, `/parrot.gif`, or invalid ones to trigger `404.html`.

## Key Concepts Demonstrated

- Low-level socket programming in Java
- Manual HTTP request parsing and response handling
- Thread-based concurrency model
- Serving static resources using file I/O
- HTTP error handling (400 and 404)

## Attribution

This project was developed as part of an assignment for **CSCI3363: Computer Networks**, taught by **Professor Wiseman** at **Boston College**. While the overall structure and assignment goals were provided by the instructor, the implementation and logic were developed by myself and are presented here for demonstration purposes.

## License

This project is shared for educational and portfolio purposes. Redistribution or reuse must include appropriate attribution and must not be used for academic credit without authorization.
