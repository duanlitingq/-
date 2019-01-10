#好物订单 订单购买人,购买人电话,购买人地址
alter  table  hsh_supplier_order add column BUY_USER_NAME VARCHAR (255) COMMENT "购买人姓名";

alter table  hsh_supplier_order add column  ADDRESS VARCHAR (255) COMMENT "购买地址";

alter table  hsh_supplier_order add column  BUY_USER_PHONE VARCHAR (255) COMMENT "购买电话";



