# Dizplai Polling Technical Task

## Implementation detail
* Relational DB chosen a data is structured and structure of the data is known
  * NoSQL DB could have been a valid option here too for its greater availability and write throughput
* Caching used to reduce load on DB each time vote is cast

# API Definition
### Create Poll
Request
POST `http://localhost:8080/api/v1/polls`
```json
{
  "question":"Who is the best this time?",
  "pollAnswers": [
    {"answer": "Me"},
    {"answer": "You"},
    {"answer": "Them"},
    {"answer": "Him"},
    {"answer": "Her"},
    {"answer": "Another"}
  ]
}
```
Response
```json
{
  "poll": {
    "id": "38e7fd68-6c67-48be-847b-bfebce73545e",
    "question": "Who is the best this time?",
    "answers": [
      {
        "id": "6f39740d-1889-42b4-8e3d-88211ad42c50",
        "answer": "Me"
      },
      {
        "id": "52844d04-36bb-4e8a-9f05-84a04fcf381b",
        "answer": "You"
      },
      {
        "id": "c7df2b70-4460-4921-a025-ffc482e42037",
        "answer": "Them"
      },
      {
        "id": "c244a3e0-a0a7-48ac-ba47-886a7208e7a2",
        "answer": "Him"
      },
      {
        "id": "29da9a20-2a3a-40a0-b5e5-507e88fc37d7",
        "answer": "Her"
      },
      {
        "id": "2a4a9e7f-47b8-4769-a325-7fc161d91e0b",
        "answer": "Another"
      }
    ]
  }
}
```

### Answer poll
Request

POST `http://localhost:8080/api/v1/polls/{{pollId}}/votes`
```json
{
  "pollAnswerId":"25279613-faa8-4f64-86c2-f788d5f78b90"
}
```

Response
```json
{
  "answer": {
    "pollId": "0070ea2c-f956-42d1-a71c-8c3829b64a92",
    "selectedAnswerId": "25279613-faa8-4f64-86c2-f788d5f78b90",
    "userAnswerId": "5ba8bc88-3ef8-471d-952f-ad2af2cecc19",
    "createdAt": "2025-02-14T16:50:27.391816Z"
  },
  "pollStatistics": {
    "total": 216,
    "answerStatistics": [
      {
        "answerId": "25279613-faa8-4f64-86c2-f788d5f78b90",
        "percentage": 55,
        "total": 119
      },
      {
        "answerId": "f94cf811-a80e-471c-921f-9f36bd7ce666",
        "percentage": 45,
        "total": 97
      }
    ]
  }
}
```

### Get poll answers
Request

GET `http://localhost:8080/api/v1/polls/{{pollId}}/votes?page=21&size=10&sort=DESC`

Response
```json
{
  "elements": [
    {
      "pollId": "0070ea2c-f956-42d1-a71c-8c3829b64a92",
      "selectedAnswerId": "f94cf811-a80e-471c-921f-9f36bd7ce666",
      "userAnswerId": "c51b2f78-b385-411f-ae9c-7e29bf61febd",
      "createdAt": "2025-02-14T15:57:54.148136Z"
    },
    {
      "pollId": "0070ea2c-f956-42d1-a71c-8c3829b64a92",
      "selectedAnswerId": "f94cf811-a80e-471c-921f-9f36bd7ce666",
      "userAnswerId": "31839864-d5fd-454c-9741-eaacae604c07",
      "createdAt": "2025-02-14T15:57:54.037755Z"
    },
    {
      "pollId": "0070ea2c-f956-42d1-a71c-8c3829b64a92",
      "selectedAnswerId": "f94cf811-a80e-471c-921f-9f36bd7ce666",
      "userAnswerId": "6fdeb9f4-3e6d-4d4d-bbc9-8422a6251a3c",
      "createdAt": "2025-02-14T15:57:53.856627Z"
    },
    {
      "pollId": "0070ea2c-f956-42d1-a71c-8c3829b64a92",
      "selectedAnswerId": "f94cf811-a80e-471c-921f-9f36bd7ce666",
      "userAnswerId": "4f251af2-b609-4c65-850d-c3fde8a5a660",
      "createdAt": "2025-02-14T15:57:53.422186Z"
    },
    {
      "pollId": "0070ea2c-f956-42d1-a71c-8c3829b64a92",
      "selectedAnswerId": "f94cf811-a80e-471c-921f-9f36bd7ce666",
      "userAnswerId": "fcd0599b-93ac-46b6-a985-b0a90da21b9d",
      "createdAt": "2025-02-14T15:57:53.054922Z"
    },
    {
      "pollId": "0070ea2c-f956-42d1-a71c-8c3829b64a92",
      "selectedAnswerId": "25279613-faa8-4f64-86c2-f788d5f78b90",
      "userAnswerId": "4d1f9a62-47ac-4c56-95f2-cf9342719969",
      "createdAt": "2025-02-14T15:48:32.260139Z"
    }
  ],
  "page": 21,
  "size": 6,
  "totalElements": 216,
  "totalPages": 22
}
```

### Get latest poll
Request

GET `http://localhost:8080/api/v1/polls`

Response
```json
{
  "id": "aa4ad4b7-5893-4695-8515-47efa24f6cdc",
  "question": "Who is the best this time?",
  "answers": [
    {
      "id": "ad35bc17-bc39-45cd-baba-555f2f45a07f",
      "answer": "Me"
    },
    {
      "id": "da2e35e9-dfee-41ba-b735-b983d0653243",
      "answer": "You"
    }
  ]
}
```
