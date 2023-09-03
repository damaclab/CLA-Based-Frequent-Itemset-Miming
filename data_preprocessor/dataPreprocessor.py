def data_preprocessor():
    total_transactions = 0
    total_items = 0

    file_w = open('retailPreprocessed.txt', 'a')
    file = open('retail.txt', 'r')

    for line in file:
        total_transactions = total_transactions + 1
        line = line.rstrip()
        line = line.replace(" ", ",")
        file_w.write( line.rstrip() + "\n")
        item_ids = line.split(",")

        for item_id in item_ids:
            if int(item_id) > int(total_items):
                total_items = item_id


    print("Total Items: " + str(total_items))
    print("Total Transactions: " + str(total_transactions))

    file_w.close()
    file.close()


if __name__ == "__main__":
    data_preprocessor()