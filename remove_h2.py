import re

with open("pom.xml", "r") as f:
    content = f.read()

# Pattern to match H2 dependency
pattern = re.compile(r'<dependency>\s*<groupId>com.h2database</groupId>\s*<artifactId>h2</artifactId>\s*<scope>runtime</scope>\s*</dependency>', re.DOTALL)
new_content = re.sub(pattern, '', content)

with open("pom.xml", "w") as f:
    f.write(new_content)
