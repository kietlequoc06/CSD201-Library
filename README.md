# CSD201Library

Thư viện Java tự viết để học và ôn thi thực hành môn CSD201.

## Yêu cầu môi trường

- Apache NetBeans IDE
- JDK 8 trở lên

## Mở project và Clean and Build

1. **File → Open Project…**, chọn thư mục `CSD201Library`, nhấn **Open Project**.
2. Chuột phải vào project → **Clean and Build** (hoặc `Shift + F11`).

## Chạy Demo

Trong **Source Packages**, mở file `...Demo`, chuột phải → **Run File** (hoặc `Shift + F6`).

## Cheatsheet

- [`CSD201_CheatSheet.pdf`](CSD201_CheatSheet.pdf): tra cứu.
- [`CSD201_CheatSheet.tex`](CSD201_CheatSheet.tex): chỉnh sửa hoặc biên dịch bằng XeLaTeX / LuaLaTeX.

## Cấu trúc thư mục

```
CSD201Library/
├── src/                  Mã nguồn chính
│   ├── list/
│   ├── stackAndQueue/
│   ├── hashTable/
│   ├── recursion/
│   ├── tree/
│   ├── sorting/
│   └── graph/
├── nbproject/            Cấu hình NetBeans
├── build.xml             Cấu hình
├── manifest.mf           Cấu hình
├── test/                 Do NetBeans tạo
├── build/                Sinh tự động khi build
├── data.txt              Sinh tự động khi chạy SortingDemo
├── CSD201_CheatSheet.pdf
└── CSD201_CheatSheet.tex
```

## Tra cứu nhanh

| Package | Class chính | Chức năng |
|---------|-------------|-----------|
| `list` | `MyList` | Danh sách liên kết đơn (`int`) |
| `stackAndQueue` | `MyStack`, `MyQueue` | Ngăn xếp, hàng đợi (`Object`) |
| `hashTable` | `MyHashTable` | Bảng băm nối chuỗi, 10 bucket |
| `recursion` | `Recursion` | Các bài đệ quy |
| `tree` | `BSTree` | Cây nhị phân tìm kiếm |
| `sorting` | `Sorting` | Merge, Quick, Selection, Insertion, Bubble Sort |
| `graph` | `Graph` | Đồ thị ma trận kề: BFS, DFS, Dijkstra, Euler |
