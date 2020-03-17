```
/**
 * 发送消息中心消息，每5000个用户消息从线程池中另启一线程发送
 * @param message
 * @param customerList
 */
public void sendMsgMessage(final SysMessage message, List<CustomerMainInfo> customerList){
    if(customerList.isEmpty()) return;
    final Integer size = customerList.size();
    final int count = size%MSG_MAX_SIZE == 0? size/MSG_MAX_SIZE : size/MSG_MAX_SIZE+1;
    final CountDownLatch latch = new CountDownLatch(count);
    ExecutorService executor = Executors.newFixedThreadPool(count);
    for(int i = 0; i < count;i++){
        final int index = i;
        final List<CustomerMainInfo> list = customerList;
        executor.execute(new Runnable() {
            public void run() {
                MsgMessageSendDto sendDto = new MsgMessageSendDto();
                sendDto.setMerchantSerialNo(message.getId().toString());
                sendDto.setTitle(message.getMsgTitle());
                sendDto.setSummary(message.getMsgSummary());
                sendDto.setContent(message.getMsgContent());
                sendDto.setType(Integer.valueOf(message.getMsgType()));
                if (ContentType.ACTIVITY.getValue().toString().equals(message.getMsgType())) {
                    sendDto.setContentType(ContentType.ACTIVITY);
                }
                if (ContentType.WELFARE.getValue().toString().equals(message.getMsgType())) {
                    sendDto.setContentType(ContentType.WELFARE);
                }
                if (ContentType.OTHER.getValue().toString().equals(message.getMsgType())) {
                    sendDto.setContentType(ContentType.OTHER);
                }
                sendDto.setInscriber(message.getMsgInscribe());
                //sendDto.setCallbackUrl(Constant.NOTIFY_URL+Constant.MESSAGE_CALL_BACK);
                sendDto.setCallbackUrl(configPropsBean.getNotify_url()+configPropsBean.getMessage_callBack());
                List<MsgMessageSendDto.UserDto> users = new ArrayList<>();
                List<CustomerMainInfo> subList;
                if (index == count - 1) {
                    subList = list.subList(index * MSG_MAX_SIZE, size);
                } else {
                    subList = list.subList(index * MSG_MAX_SIZE, (index + 1) * MSG_MAX_SIZE);
                }
                for (CustomerMainInfo customer : subList) {
                    MsgMessageSendDto.UserDto userOne = new MsgMessageSendDto.UserDto(String.valueOf(customer.getId()), customer.getCmNumber(), customer.getCmRealName(), customer.getCmCellphone());
                    users.add(userOne);
                }
                sendDto.setUsers(users);
                try {
                    MessageResultDto resultDto = iMsgMessageFacadeService.send(sendDto);
                    if (resultDto.isSuccess()) {
                        logger.info("分批发送消息成功,分" + count + "批,此为第" + (index + 1) + "批,标题:" + sendDto.getTitle());
                    } else {
                        logger.info("分批发送消息失败,分" + count + "批,此为第" + (index + 1) + "批,标题:" + sendDto.getTitle() + ",失败码" + resultDto.getCode() + ",失败原因" + resultDto.getMsg());
                    }
                } catch (Exception e) {
                    logger.error("分批发送消息失败,分" + count + "批,此为第" + (index + 1) + "批,标题:" + sendDto.getTitle() + "异常信息:" + e.getMessage());
                }finally {
                    latch.countDown();
                }
            }
        });
    }
    //当所有线程任务完成，执行主线程，修改消息发送状态
    try {
        latch.await();
        //通知gc要准备收回内存资源
        customerList.clear();
    } catch (InterruptedException e) {
        logger.error(e.getMessage());
    }finally {
        executor.shutdown();
    }
}
```