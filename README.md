# WOB-Homework 

## Getting Started

### Prerequisites

In the `application.properties` you have to set your own MySQL and FTP server details:

```ftp.url=```

```ftp.username=```

```ftp.password=```

```spring.datasource.url=```

```spring.datasource.username=```

```spring.datasource.password=```


## Test the application

### Profiles

The application contains a mock API service to avoid communication with the remote API. It reads the mock response from a file. To use this mock service, just run the application with ```mock``` profile.


## Authors

* **Tamas Meszaros**
