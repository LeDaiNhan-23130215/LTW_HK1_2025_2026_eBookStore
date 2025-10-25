// api-mock.js - dữ liệu mẫu dưới dạng JS (không .json)
export const BOOKS = [
  { id: 1, title: "Sample Book 1", author: "Author A", price: 120000, type: "ebook" },
  { id: 2, title: "Sample Book 2", author: "Author B", price: 90000, type: "paper" }
];
export function getBooks(){ return BOOKS; }
