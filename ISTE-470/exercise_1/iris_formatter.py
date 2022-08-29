import sys

def validate_params(params):
    if (len(params) < 3):
        print("Usage: python3 iris_formatter.py infile_name outfile_name")
        return False
    else: 
        return True

def main():
    args = sys.argv
    print(validate_params(args))

if __name__ == "__main__":
    main()
