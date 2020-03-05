from lxml import html
import requests
import re
urls=['https://bulletins.psu.edu/university-course-descriptions/undergraduate/cyber/', 
      'https://bulletins.psu.edu/university-course-descriptions/undergraduate/ist/',
      'https://bulletins.psu.edu/university-course-descriptions/undergraduate/sra/',
      'https://bulletins.psu.edu/university-course-descriptions/undergraduate/cmpsc/',
      'https://bulletins.psu.edu/university-course-descriptions/undergraduate/econ/',
      'https://bulletins.psu.edu/university-course-descriptions/undergraduate/engl/',
      'https://bulletins.psu.edu/university-course-descriptions/undergraduate/math/',
      'https://bulletins.psu.edu/university-course-descriptions/undergraduate/stat/']
courses = []
descriptions=[]
credits=[]
attributes=[]

for url in urls:
    page = requests.get(url)
    tree = html.fromstring(page.content)
    coursetemp = tree.xpath('//div[@class="course_codetitle"]/text()')
    courses = courses + coursetemp
    for course in courses:
        if not bool(re.search(r'\d', course)):
            courses.remove(course)
    descriptions = descriptions + tree.xpath('//div[@class="courseblockdesc"]//p/text()')
    credits = credits + tree.xpath('//div[@class="course_credits"]/text()')
    '''for course in coursetemp:     
        coursestr = course
        coursestr = coursestr.split(':')[0]
        coursestr = re.sub(' ', '', coursestr)
        coursestr = 'cb-'+coursestr
        print(coursestr)
        if bool(re.search(r'\d', coursestr)):
            attributes = attributes + tree.xpath('//div[@id=coursestr]//div[@class="noindent courseblockextra"]/text()')
            print(attributes)'''

for attribute in attributes:
    if '\n' in attribute:
        attributes[attributes.index(attribute)] = re.sub('\n', '', attribute)
for attribute in attributes:
    if '\t' in attribute:
        attributes[attributes.index(attribute)] = re.sub('\t', '', attribute)
for description in descriptions:
    if '\n' in description:
        descriptions[descriptions.index(description)] = re.sub('\n', '', description)
for description in descriptions:
    if '\t' in description:
        descriptions[descriptions.index(description)] = re.sub('\t', '', description)
for credit in credits:
    if '\n' in credit:
        credits[credits.index(credit)] = re.sub('\n', '', credit)
for credit in credits:
    if '\t' in credit:
        credits[credits.index(credit)] = re.sub('\t', '', credit)

print('Courses: ', courses)
print('Descriptions: ', descriptions)
print('Credits: ', credits)
print('Attributes: ', attributes)

file = open("courselist.txt", "w+")
for course in courses:
    if course.lstrip().startswith('ENGL') or course.lstrip().startswith('CYBER') or course.lstrip().startswith('ECON') or course.lstrip().startswith('IST') or course.lstrip().startswith('MATH') or course.lstrip().startswith('STAT') or course.lstrip().startswith('SRA') or course.lstrip().startswith('CMPSC'):
        file.write(course + "\t" + credits[courses.index(course)] + "\t" + descriptions[courses.index(course)] + "\n") #+ attributes[courses.index(course)] + "\n")
        
