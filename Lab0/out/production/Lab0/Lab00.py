'''
CPE 203 - Lab00
Convert this py file to a java file!
'''
def main():

   # declaring and initializing some variables

   x = 12

   y = 'WELCOME to CSC/CPE203'

   z = 3.1415

   # printing the variables

   print('x:', x, 'y:', y, 'z:', z)

   # a list (make an array in java)

   nums = [5, 16, -8, 2, 11]

   for num in nums:

      print(num)


   # call a function

   numFound = char_count(y, 'C')

   print("Number of C in ", y)

   print("Found:", numFound)

   # a counting for loop

   for i in range(1,15):

      print(i, end=' ')

   print()

#function counts the given character in the given string
#str str -> int
def char_count(s:str, c:str)-> int:

   count = 0

   for ch in s:

      if ch == c:

         count += 1

   return count


if __name__ == "__main__":

   main()
