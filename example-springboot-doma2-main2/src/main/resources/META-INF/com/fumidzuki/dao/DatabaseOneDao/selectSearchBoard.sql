select
    *
from
    test.board
where
    /*%if author != null */
    author like /* @infix(author) */'작성자'
    /*%elseif title != null */
    or
    title like /* @infix(title) */'제목100'
    /*%elseif contents != null */
    or
    contents like /* @infix(contents) */'내용100'
    /*%end*/
order by
    idx desc
