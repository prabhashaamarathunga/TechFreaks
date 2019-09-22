package com.example.mobilemania;

public class CreditorsProvider {

    private String name;
    private String phone;
    private String amount;
    private String date;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

    public CreditorsProvider(String name, String phone, String amount, String date){
        this.name = name;
        this.phone = phone;
        this.amount = amount;
        this.date = date;

    }

}
