import urllib.request
import json
import time
import subprocess

# Start the Spring Boot app
process = subprocess.Popen(["./mvnw", "spring-boot:run"], stdout=subprocess.PIPE, stderr=subprocess.PIPE)

# Wait for it to start
time.sleep(15)

def post_event(event_type):
    url = "http://localhost:8080/api/matches/1/events"
    data = json.dumps({
        "inningsId": 1, "strikerId": 1, "nonStrikerId": 2, "bowlerId": 3, "eventType": event_type
    }).encode("utf-8")
    req = urllib.request.Request(url, data=data, headers={'Content-Type': 'application/json'})
    urllib.request.urlopen(req)

def get_score():
    url = "http://localhost:8080/api/matches/1/score"
    req = urllib.request.Request(url)
    with urllib.request.urlopen(req) as response:
        return json.loads(response.read().decode())

try:
    post_event("FOUR")
    print("After FOUR:", get_score())

    post_event("WICKET")
    print("After WICKET:", get_score())

    post_event("SIX")
    print("After SIX:", get_score())
except Exception as e:
    print("Error:", e)
finally:
    process.terminate()
