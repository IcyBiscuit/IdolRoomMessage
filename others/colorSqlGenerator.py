from colorInfo import info

colors = set()

file = open('./t_support_color.sql', 'w')
file.write('insert into %s\n(%s,%s,%s)\nvalues' %
           ('t_colorinfo', 'color_name', 'background_color', 'border_color'))

for member in info:
    if member['supportColor'] not in colors:
        colors.add(member['supportColor'])
        text = '(\'%s\',\'%s\',\'%s\'),\n' % (
            member['supportColor'], member['backgroundColor'], member['borderColor'])
        file.write(text)

file.flush()
file.close()
