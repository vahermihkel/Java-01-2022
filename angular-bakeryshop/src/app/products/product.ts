export class Product {

  constructor(name: string, price: number, quantity: number, store: string, id?: number) {
    this.name = name;
    this.price = price;
    this.quantity = quantity;
    this.store = store;
    this.id = id;
  }

  name: string;
  price: number;
  quantity: number;
  store: string;
  id?: number;
}
