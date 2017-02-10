
SELECT
    C.book_seq
  , C.title
FROM
    td_series      A
  , tr_series_book B
  , td_book        C
WHERE
    A.series_seq = /* seriesSeq */0
AND A.series_seq = B.series_seq
AND B.book_seq   = C.book_seq
ORDER BY
    B.no
