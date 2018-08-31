from colorInfo import info

memberColorList = {}

for member in info:
    if member['memberName'] not in memberColorList:
        memberColorList[member['memberName']] = {
            'backgroundColor': member['backgroundColor'],
            'borderColor': member['borderColor']
        }

print(memberColorList)