from random import randint
import math

# Fill a group with random objects of a given list of elements.
def fill_group(groups, group_index, elements, groups_size, difference):
    while (len(groups[group_index]) < (groups_size + difference)) and (len(elements) > 0):
        random_element = randint(0, len(elements) - 1)
        groups[group_index].append(elements[random_element])
        del elements[random_element]

def distribute_elements(elements, groups_size, groups_max = float('inf')):
    groups_max = int(len(elements) / size) if groups_max == float('inf') else groups_max
    groups = [] # Store the elements in groups.

    # Generate groups of elements while all elements are placed in a group.
    while len(elements):
        groups_quantity = len(groups)
        exceeds_groups_max = groups_quantity >= groups_max

        # Distribute elements in the current group.
        if not exceeds_groups_max:
            groups.append([])
            fill_group(groups, groups_quantity, elements, groups_size, 0)
            groups_quantity += 1
        else:
            for i in range(len(elements)):
                random_group = randint(0, groups_quantity - 1)

                while len(groups[random_group]) >= groups_size + 1:
                    random_group = randint(0, groups_quantity - 1)

                fill_group(groups, random_group, elements, groups_size, 1)
            break

    return groups

# Request and store the quantity of groups.
print('N° estudiantes por grupo: ', end = '')
size = int(input())

print('URL de archivo de estudiantes: ', end = '')
students_url = str(input())

print('URL de archivo de temas: ', end = '')
topics_url = str(input())

# Get the list of students.
with open(students_url) as f:
    students = f.readlines()

# Get the list of topics.
with open(topics_url) as f:
    topics = f.readlines()

# Distribute student in groups.
student_groups = distribute_elements(students, size)

# Distribute topics in student groups.
topics_groups_size = int(len(topics) / len(student_groups))
topics_groups_max = len(student_groups)

topic_groups = distribute_elements(topics, topics_groups_size, topics_groups_max)

# Print the result
print('')
count = 0
for i in range(len(student_groups)):
    print(f'Grupo {count + 1}:')

    print('  - Estudiantes')
    for value in student_groups[i]:
        print(f'    - ', value, end = '')

    print('  - Temas')
    for value in topic_groups[i]:
        print(f'    - ', value, end = '')

    print('')
