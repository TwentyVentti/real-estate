import json

with open('data.json') as f:
    data = json.load(f)
    print(type(data))

for it in data:
    temp = 5
    for jt in data[it]:
        if jt["id"] > 1000:
            if jt["id"] % 100 == 0:
                jt["id"] = (jt["id"] // 1000) * 100 +  + (temp//5)
            else:
                jt["id"] = (jt["id"] // 100) * 100 +  + (temp//5)
        else:
            jt["id"] = (jt["id"] // 10) * 100 +  + (temp//5)
        temp += 1


levelDict = {1:1, 2:1, 3:1, 4:1, 5:1}

for it in data:
    temp = data[it][0]["level"]
    for jt in data[it]:
        jt["id"] = (jt["id"]//1000) * 10000 + levelDict[temp] * 1000 + (jt["id"]%1000)
    levelDict[temp] += 1

data = {k: v for k, v in sorted(data.items(), key=lambda item: item[1][0]["id"])}

with open('temp.json', 'w', encoding='utf8') as json_file:
    json.dump(data, json_file, indent = 4, ensure_ascii=False)
