package models

import slick.jdbc.MySQLProfile.api._


class SampleObj {

   class Suppliers(tag: Tag) extends Table[(Int, String, String, String, String, String)](tag, "SUPPLIERS") {
      def id = column[Int]("ID", O.PrimaryKey)
      def name = column[String]("NAME")
      def street = column[String]("STREET")
      def city = column[String]("CITY")
      def state = column[String]("STATE")
      def zip = column[String]("ZIP")
      // は全てのテーブルで * 射影をテーブルの型パラメータに合うように定義する
      def * = (id, name, street, city, state, zip)
    }

   val suppliers = TableQuery[Suppliers]

    class Coffees(tag: Tag) extends Table[(String, Int, Double, Int, Int)](tag, "COFFEES") {
      def name = column[String]("COF_NAME", O.PrimaryKey)
      def supID = column[Int]("SUP_ID")
      def price = column[Double]("PRICE")
      def sales = column[Int]("SALES")
      def total = column[Int]("TOTAL")
      def * = (name, supID, price, sales, total)
      // joinなどを発行する際に用いられる外部キー
      def supplier = foreignKey("SUP_FK", supID, suppliers)(_.id)
    }

    val coffees = TableQuery[Coffees]
}