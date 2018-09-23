import requests

url = 'http://localhost:8864/rank/ajax/pocketdata/'

resp = requests.post(url, data={
    'roomId': '5779220'
})

print(resp.content)
