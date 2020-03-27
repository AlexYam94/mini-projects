import yfinance as yf
import pprint
import pandas as pd
import requests
# import progressbar
from datetime import datetime

def read_data(source, key):
    try:
        result = source[key]
    except:
        result = "0"
    finally:
        return result

def cal_diff(var1, var2):
    print("cal_diff")
    print("var1:")
    print(var1)
    print("var2")
    print(var2)
    result = (float(var1)-float(var2))/float(var2) * 100
    print(result)
    return result

def cal_eps(net_income, common_shares):
    return net_income/common_shares

def check_is_zero(var):
    for x in var:
        if float(x) <=0:
            return True
    return False

def check_balance_sheet(date,cash,current_asset,current_liab,total_liab,total_asset,long_term_debt):
    result = True
    try:
        if check_is_zero(cash) and check_is_zero(current_asset) and check_is_zero(total_asset):
            print("balance_sheet has zero")
            result = False
        else:
            # cash > long_term_debt
            if not (float(long_term_debt[0])<=0) and not (float(cash[0])/float(long_term_debt[0]) > 1):
                result = False
            elif float(long_term_debt[0]) == 0:  
                result = True
            #current_asset > total_liab
            if not float(total_liab[0])<=0 and not (float(total_asset[0])/float(total_liab[0]) > 1):
                result = False
    except Exception as e:
        print(e)
        result = False
    finally:
        return result 

def check_income_statement(total_revenue, gross_profit, net_income):
    try:
        result = True
        if check_is_zero(total_revenue) and check_is_zero(gross_profit) and check_is_zero(net_income):
            print("income_statement has zero")
            result = False
        else:
            print("total_revenue:")
            print(total_revenue)
            if not (cal_diff(total_revenue[0],total_revenue[1]) >= 10 or  cal_diff(total_revenue[1], total_revenue[2]) >= 10 or cal_diff(total_revenue[2],total_revenue[3]) >= 10):
                result=False
    except Exception as e:
        print(e)
        result=False
    finally:
        return result

def check_cash_flow():
    return False


# list of strings
# data = {'Name':['Jai', 'Princi', 'Gaurav', 'Anuj'],
#         'Age':[27, 24, 22, 32],
#         'Address':['Delhi', 'Kanpur', 'Allahabad', 'Kannauj'],
#         'Qualification':['Msc', 'MA', 'MCA', 'Phd']}

# # Calling DataFrame constructor on list
# df = pd.DataFrame(data)
# for (columnName, columnData) in df.iteritems():
#     print(columnName)
#     print(columnData)

# bar = progressbar.ProgressBar(maxval=3517, widgets=[progressbar.Bar('=','[',']'),' ', progressbar.Percentage()])
# bar = progressbar.ProgressBar(maxval=3517, widgets=['Processed: ', progressbar.Counter(), ' lines (', progressbar.Timer(), ')'])
# bar.start()
pp = pprint.PrettyPrinter(indent=4)

with open('nasdaqlisted.txt') as f:
    raw_list = list(f)

raw_list.pop(0)


symbol_list = []
for x in raw_list:
    symbol_list.append(x[0:x.find('|')])

# stock_list = []
processed_symbol = []

i = 0

stock_list = open("stock_list.txt","w")

for x in symbol_list:
    print("\n" + datetime.now().strftime("%H:%M:%S"))
    print(x)
    print("cp1")
    # x = "AAPL"
    try:
        # if i > 3:
        #     break
        # i+=1
        ticker = yf.Ticker(x)
        print("cp2")
        processed_symbol.append(x)
        print("cp3")
        # print("\n"+ticker.info['symbol'] + "\n")
        # print(ticker)
        # pp.pprint(ticker.info)
        date = []
        cash = []
        current_asset = []
        current_liab = []
        total_liab = []
        total_asset = []
        long_term_debt = []
        total_revenue = []
        gross_profit = []
        net_income = []
        quarterly_balance_sheet = ticker.quarterly_balance_sheet
        print("cp4")
        # print(quarterly_balance_sheet)
        # print("\n")
        for j in quarterly_balance_sheet:
            # print(j)
            column = quarterly_balance_sheet[j]
            # print(column)
            # print("\n")
            date.append(str(column.name))
            cash.append(read_data(column,'Cash'))
            current_asset.append(read_data(column,'Total Current Assets'))
            current_liab.append(read_data(column,'Total Current Liabilities'))
            total_liab.append(read_data(column,'Total Liab'))
            total_asset.append(read_data(column,'Total Assets'))
            long_term_debt.append(read_data(column,'Long Term Debt'))
            # break


        quarterly_income_statement = ticker.quarterly_financials
        print("cp6")
        for j in quarterly_income_statement:
            column = quarterly_income_statement[j]
            total_revenue.append(read_data(column,"Total Revenue"))
            gross_profit.append(read_data(column, "Gross Profit"))
            net_income.append(read_data(column, "Net Income"))

        # quarterly_cash_flow = ticker.quarterly_cashflow
        # print("cp7")
        # for j in quarterly_cash_flow:
        #     column = quarterly_cash_flow[j]
        #     total

        result1 = check_balance_sheet(date,cash,current_asset,current_liab,total_liab,total_asset,long_term_debt)
        result2 = check_income_statement(total_revenue, gross_profit, net_income)

        print(result1)
        print(result2)
        if result1 and result2:
            print("writing to file")
            stock_list.write(x+"\n")
            stock_list.flush()
    except requests.Timeout as e:
        print("Timeout")
        i-=1
    except Exception as e:
        print(e)
        continue  
    finally:
        print("finally")
        i+=1
        print(i)
        # break
        # bar.update(i)

    




