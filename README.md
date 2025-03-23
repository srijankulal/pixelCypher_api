# PixelCypher

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.3-green)
![Java](https://img.shields.io/badge/Java-17-orange)

A Spring Boot REST API for image steganography - hiding and retrieving text messages within image files.

## Overview

PixelCypher API allows users to:

- Encode secret text messages within images
- Decode text messages from images containing hidden data
- Minimal impact on image appearance while securely storing data

## How It Works

PixelCypher uses Least Significant Bit (LSB) steganography to embed text within image pixels. This technique modifies only the least significant bit of each pixel's color value, making the changes virtually imperceptible to the human eye.

## API Endpoints

### Encode Text into Image

```
POST /api/encode
Content-Type: multipart/form-data

{
    "image": (file),
    "text": "Your secret message here"
}
```

### Decode Text from Image

```
POST /api/decode
Content-Type: multipart/form-data

{
    "image": (file)
}
```

## Technologies Used

- **Backend**: Java Spring Boot
- **Image Processing**: Java AWT & ImageIO
- **Build Tool**: Maven

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven

### Running Locally

1. Clone the repository

   ```
   git clone https://github.com/yourusername/pixelCypher.git
   cd pixelCypher
   ```

2. Build the project

   ```
   ./mvnw clean package
   ```

3. Run the API

   ```
   ./mvnw spring-boot:run
   ```

4. Access the API
   - The endpoints will be available at: http://localhost:8080
   - API documentation is available at the root URL

## Frontend Integration

This API is configured to work with a frontend hosted at `https://pixelcypher-app.vercel.app`. The CORS configuration allows secure cross-origin requests from this domain.

## Implementation Details

The steganography algorithm:

1. Converts text message to binary data
2. Embeds each bit into the least significant bit of pixels' color values
3. Adds a null terminator to mark the end of the message
4. For extraction, reads the LSB of each pixel until finding the null terminator

## Security Considerations

This implementation provides basic steganographic capabilities. For production use, consider adding:

- Encryption of the message before embedding
- Password protection for the encoding/decoding process
- Message integrity verification

## License

[MIT License](LICENSE)

## Contact

Developed by [Srijan K](https://github.com/srijankulal)
