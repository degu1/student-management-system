# Lab 1 Dennis Gustafsson

This service handles student. The student entity contains the filed:

- id, is generated in the service and is an integer.
- firstName, mandatory.
- lastName, mandatory.
- phoneNumber, not mandatory.
- email, mandatory and unique.

## Endpoints

- Get all students <br>
  GET http://localhost:8080/student-management-system/api/v1/students <br>
  Example json response:

```json lines
[
  {
    "email": "dennis1@mail.com",
    "firstName": "Dennis",
    "id": 1,
    "lastName": "Gustafsson",
    "phoneNumber": "012312"
  },
  {
    "email": "dennis2@mail.com",
    "firstName": "Dennis",
    "id": 2,
    "lastName": "Gustafsson",
    "phoneNumber": "012312"
  },
  {
    "email": "dennis3@mail.com",
    "firstName": "Dennis",
    "id": 3,
    "lastName": "Gustafsson",
    "phoneNumber": "012312"
  }
]
```

- Save new student <br>
  POST http://localhost:8080/student-management-system/api/v1/students <br>
  Example of json body:

```json lines
{
  "firstName": "Dennis",
  "lastName": "Gustafsson",
  "email": "dennis@mail.com",
  "phoneNumber": "012312"
}
```

- Update student with PUT<br>
  PUT http://localhost:8080/student-management-system/api/v1/students <br>
  Example of json body:

```json lines
{
  "email": "dennis@mail.com",
  "firstName": "Nils",
  "id": 1,
  "lastName": "Gustafsson",
  "phoneNumber": "012312"
}
```

- Update student with PATCH <br>
  PATCH http://localhost:8080/student-management-system/api/v1/students <br>
  Example of json body:

```json lines
{
  "firstName": "Nils",
  "id": 1
}
```

- Get student by id <br>
  GET http://localhost:8080/student-management-system/api/v1/students/{id} <br>
  Example of json response when {id} is 1:

```json lines
{
  "email": "dennis@mail.com",
  "firstName": "Dennis",
  "id": 1,
  "lastName": "Gustafsson",
  "phoneNumber": "012312"
}
```

- Delete student by id <br>
  DELETE http://localhost:8080/student-management-system/api/v1/students/{id} <br>
- Get student by last name <br>
  GET http://localhost:8080/student-management-system/api/v1/students/lastname?lastname={lastname} <br>
  Example of json response when {lastname} is Gustafsson:

```json lines
{
  "email": "dennis@mail.com",
  "firstName": "Dennis",
  "id": 1,
  "lastName": "Gustafsson",
  "phoneNumber": "012312"
}
```

- Add subject to student <br>
  POST http://localhost:8080/student-management-system/api/v1/students/add_subject/{studentId}/{subjectId} <br>
- Remove subject to student <br>
  DELETE http://localhost:8080/student-management-system/api/v1/students/remove_subject/{studentId}/{subjectId} <br>

- Create subject <br>
  POST http://localhost:8080/student-management-system/api/v1/subjects <br>
  Example of json body:

```json lines
{
  "name": "Math"
}  
```

- Update subject with PUT <br>
  PUT http://localhost:8080/student-management-system/api/v1/subjects <br>
  Example of json body:

```json lines
{
  "id": 3,
  "name": "Java"
}  
```

- Update subject with PUT <br>
  PUT http://localhost:8080/student-management-system/api/v1/subjects <br>
  Example of json body:

```json lines
{
  "id": 3,
  "name": "Java"
}  
```

- Get all subject <br>
  GET http://localhost:8080/student-management-system/api/v1/subjects <br>
  Example of json response:

```json lines
[
  {
    "id": 3,
    "name": "Java",
    "students": [
      {
        "email": "bullen@mail.com",
        "firstName": "Bullen",
        "id": 1,
        "lastName": "Bullenson",
        "phoneNumber": "123"
      },
      {
        "email": "dennis@mail.com",
        "firstName": "Dennis",
        "id": 2,
        "lastName": "Gustafsson",
        "phoneNumber": "123"
      }
    ],
    "teacher": {
      "id": 4,
      "name": "Teacher"
    }
  }
]
```

- Get subject by id <br>
  GET http://localhost:8080/student-management-system/api/v1/subjects/{id} <br>
  Example of json response:

```json lines
{
  "id": 3,
  "name": "Java",
  "students": [
    {
      "email": "bullen@mail.com",
      "firstName": "Bullen",
      "id": 1,
      "lastName": "Bullenson",
      "phoneNumber": "123"
    },
    {
      "email": "dennis@mail.com",
      "firstName": "Dennis",
      "id": 2,
      "lastName": "Gustafsson",
      "phoneNumber": "123"
    }
  ],
  "teacher": {
    "id": 4,
    "name": "Teacher"
  }
}
```

- Remove subject by id <br>
  DELETE http://localhost:8080/student-management-system/api/v1/subjects/{id} <br>

- Create teacher <br>
  POST http://localhost:8080/student-management-system/api/v1/teachers <br>
  Example of json body:

```json lines
{
  "name": "teacher"
}  
```

- Update teacher with PUT <br>
  PUT http://localhost:8080/student-management-system/api/v1/teachers <br>
  Example of json body:

```json lines
{
  "id": 4,
  "name": "Kalle"
}  
```

- Update teacher with PATCH <br>
  PATCH http://localhost:8080/student-management-system/api/v1/teachers <br>
  Example of json body:

```json lines
{
  "id": 4,
  "name": "Kalle"
}  
```

- Get all teacher <br>
  GET http://localhost:8080/student-management-system/api/v1/teachers <br>
  Example of json response:

```json lines
[
  {
    "id": 4,
    "name": "Kalle"
  }
]
```

- Get teacher by id <br>
  GET http://localhost:8080/student-management-system/api/v1/teachers/{id} <br>
  Example of json response:

```json lines
{
  "id": 4,
  "name": "Kalle"
}
```

- Delete teacher by id <br>
  DELETE http://localhost:8080/student-management-system/api/v1/teachers/{id} <br>

- Add subject to teacher <br>
  POST http://localhost:8080/student-management-system/api/v1/teachers/add_subject/{teacherId}/{subjectId} <br>

- Delete subject from teacher <br>
  DELETE http://localhost:8080/student-management-system/api/v1/teachers/remove_subject/{subjectId} <br>

