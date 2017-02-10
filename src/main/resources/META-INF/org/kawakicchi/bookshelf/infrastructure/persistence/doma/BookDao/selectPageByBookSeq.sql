
SELECT
    C.page_seq
  , C.name
  , C.width
  , C.height
FROM
    td_book      A
  , tr_book_page B
  , td_page      C
WHERE
    A.book_seq = /* bookSeq */0
AND A.book_seq = B.book_seq
AND B.page_seq = C.page_seq
ORDER BY
    B.no
