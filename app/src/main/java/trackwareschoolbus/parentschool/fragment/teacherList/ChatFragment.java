//package trackwareschoolbus.parentschool.fragment.teacherList;
//
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Color;
//import android.os.Bundle;
//import androidx.core.content.ContextCompat;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//import java.util.Locale;
//
//import trackwareschoolbus.parentschool.API_new.APIs.ApiController;
//import trackwareschoolbus.parentschool.API_new.APIs.Constants;
//import trackwareschoolbus.parentschool.API_new.APIs.ListenersAndInterFaces.OnApiComplete;
//import trackwareschoolbus.parentschool.R;
//import trackwareschoolbus.parentschool.app.Application;
//import trackwareschoolbus.parentschool.basePage.BaseFragment;
//import trackwareschoolbus.parentschool.bean.GCMMessage;
//import trackwareschoolbus.parentschool.bean.MessageHistoryObject;
//import trackwareschoolbus.parentschool.bean.MessageHistoryRequest;
//import trackwareschoolbus.parentschool.bean.SendMessageRequest;
//import trackwareschoolbus.parentschool.bean.SendMessageResponse;
//import trackwareschoolbus.parentschool.chatmessageview.models.Message;
//import trackwareschoolbus.parentschool.chatmessageview.models.User;
//import trackwareschoolbus.parentschool.chatmessageview.views.ChatView;
//import trackwareschoolbus.parentschool.dataBase.DAO;
//import trackwareschoolbus.parentschool.utilityParent.DateTools;
//import trackwareschoolbus.parentschool.utilityParent.StaticValue;
//import trackwareschoolbus.parentschool.utilityParent.UtilDialogs;
//
//
//public class ChatFragment extends BaseFragment implements View.OnClickListener {
//
//    private ChatView mChatView;
//    private User teacher;
//    private User father;
//    private User mother;
//    private int sender_id = -1;
//    private GCMMessage gcmMessageFromExtra, gcmMessageToSend = null;
//
//
//    ////////////////////
//    public static ChatFragment newInstance() {
//        Bundle bundle = new Bundle();
//        ChatFragment fragment = new ChatFragment();
//        fragment.setArguments(bundle);
//        return fragment;
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_chat, null, false);
//        mChatView = (ChatView) view.findViewById(R.id.chat_view);
//        /**/
//        sender_id = getMyId();
//        /**/
//        gcmMessageFromExtra = getArguments().getParcelable("GCMMessage");
//        gcmMessageToSend = new GCMMessage("", "", gcmMessageFromExtra.getClassroomId(), gcmMessageFromExtra.getStudentId(), gcmMessageFromExtra.getStudentName(), gcmMessageFromExtra.getTeacherName(), gcmMessageFromExtra.getSchoolId(), gcmMessageFromExtra.getTeacherId(), gcmMessageFromExtra.getFatherId(), gcmMessageFromExtra.getMotherId());
//        /**/
//        getActivity().setTitle("Chat with " + gcmMessageFromExtra.getStudentName() + " " + getString(R.string.parents));
//        /**/
//        initChat();
//        requestDataBaseMessagesList();
//        /**/
//        ((TextView)view.findViewById(R.id.labTitle)).setText("Chat with " + gcmMessageFromExtra.getStudentName() + " " + getString(R.string.parents));
//        view.findViewById(R.id.barImage).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               getActivity().onBackPressed();
//
//            }
//        });
//        /**/
//        if (getArguments().containsKey("deleted") && getArguments().getBoolean("deleted")) {
//            mChatView.getBottom_tool_bar().setVisibility(View.GONE);
//        }
//
//        return view;
//
//
//    }
//
//
//    public void initChat() {
//        teacher = new User(gcmMessageFromExtra.getTeacherId(), getString(R.string.teacher), null, R.color.chate_bubble_teacher);
//        if (sender_id == gcmMessageFromExtra.getFatherId()) {
//            father = new User(gcmMessageFromExtra.getFatherId(), getString(R.string.me), null, R.color.chate_bubble_father);
//            mother = new User(gcmMessageFromExtra.getMotherId(), getString(R.string.mother), null, R.color.chate_bubble_mother);
//        } else {
//            father = new User(gcmMessageFromExtra.getFatherId(), getString(R.string.father), null, R.color.chate_bubble_father);
//            mother = new User(gcmMessageFromExtra.getMotherId(), getString(R.string.me), null, R.color.chate_bubble_mother);
//        }
//
//
//        //Set UI parameters if you need
//        mChatView.setRightBubbleColor(ContextCompat.getColor(getActivity(), R.color.grey_normal));
//        mChatView.setLeftBubbleColor(Color.WHITE);
//        mChatView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.white));
//        mChatView.setSendButtonColor(ContextCompat.getColor(getActivity(), R.color.blueGray500));
//        mChatView.setSendIcon(R.drawable.ic_action_send);
//        mChatView.setRightMessageTextColor(Color.BLACK);
//        mChatView.setLeftMessageTextColor(Color.BLACK);
//        mChatView.setUsernameTextColor(Color.BLACK);
//        mChatView.setSendTimeTextColor(Color.BLACK);
//        mChatView.setDateSeparatorColor(Color.BLACK);
//        mChatView.setInputTextHint("new message...");
//        mChatView.setMessageMarginTop(5);
//        mChatView.setMessageMarginBottom(5);
//        mChatView.setAutoScroll(false);
//        mChatView.computeScroll();
//
//        ///// BubbleClickListener
//        mChatView.setOnBubbleClickListener(new Message.OnBubbleClickListener() {
//            @Override
//            public void onClick(final Message message) {
//                if (message.isPinding()) {
//                    DAO.deletePendingMessage(getDatabase(), message.getMessageText());
//                    List<Integer> receiversIds = new ArrayList<Integer>(2);
//                    receiversIds.add(gcmMessageFromExtra.getTeacherId());
//                    gcmMessageToSend.setBody(message.getMessageText());
//                    final SendMessageRequest sendMessageRequest = new SendMessageRequest(gcmMessageToSend, gcmMessageFromExtra.getSchoolId(), gcmMessageFromExtra.getClassroomId(), sender_id, receiversIds, "teacher", "parent", "text", "staging");
//                    sendMessageToAPI(sendMessageRequest);
//                    mChatView.removeMessage(message);
//                }
//
//
//            }
//        });
//
//        //Click Send Button
//        mChatView.setOnClickSendButtonListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                List<Integer> receiversIds = new ArrayList<Integer>(2);
//                receiversIds.add(gcmMessageFromExtra.getTeacherId());
//                gcmMessageToSend.setBody(mChatView.getInputText());
//                final SendMessageRequest sendMessageRequest = new SendMessageRequest(gcmMessageToSend, gcmMessageFromExtra.getSchoolId(), gcmMessageFromExtra.getClassroomId(), sender_id, receiversIds, "teacher", "parent", "text", "staging");
//                sendMessageToAPI(sendMessageRequest);
//            }
//
//        });
//
//    }
//
//
//    private void addTextMessage(User userType, String text, String date_time, boolean pinding) {
//
//        Message.Builder message = new Message.Builder();
//        message.setUser(userType);
//        message.setRightMessage(userType.getId() == sender_id);
//        message.setMessageText(text);
//        message.hideIcon(true);
//        try {
//            Calendar instance = Calendar.getInstance(Locale.US);
//            instance.setTime(DateTools.Formats.DATE_FORMAT_LOCAL.parse(date_time));
//            message.setCreatedAt(instance);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        message.setPinding(pinding);
//        mChatView.getMessageView().setMessage(message.build());
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
////        showBackArrow(true);
//        requestMessagesList();
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        registerMessageReceiver(new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                requestMessagesList();
//            }
//        });
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//
//
//        }
//
//    }
//
//    protected void requestDataBaseMessagesList() {
//        ArrayList<MessageHistoryObject> messages = DAO.getMessages(getDatabase(), gcmMessageFromExtra.getStudentId());
//        parceMessagesToBubbles(messages);
//
//        ArrayList<MessageHistoryObject> pendingMessages = DAO.getPendingMessages(getDatabase(), gcmMessageFromExtra.getStudentId());
//
//
//        for (int i = 0; i < pendingMessages.size(); i++) {
//            addTextMessage(teacher, pendingMessages.get(i).getMessage(), pendingMessages.get(i).getDateTime(), true);
//        }
//
//
//    }
//
//    protected void requestMessagesList() {
//        DAO.deleteNotification(getDatabase(), gcmMessageFromExtra.getClassroomId(), gcmMessageFromExtra.getStudentId());
//
//        final MessageHistoryRequest messageHistoryRequest = new MessageHistoryRequest(gcmMessageFromExtra.getStudentId(), "student", gcmMessageFromExtra.getClassroomId(), "2010-01-01", "2019-01-01");
//        ApiController.getMessageHistory(getActivity(), messageHistoryRequest, new OnApiComplete<ArrayList<MessageHistoryObject>>() {
//            @Override
//            public void onSuccess(final ArrayList<MessageHistoryObject> messageHistoryObject) {
//                try{
//                    if (messageHistoryObject.size() > 0) {
//                        /**/
//                        DAO.deleteAllMessages(getDatabase());
//                        DAO.addMessages(getDatabase(), messageHistoryObject, gcmMessageFromExtra.getStudentId());
//                        /**/
//                        mChatView.getMessageView().getmMessageAdapter().clear();
//                        mChatView.getMessageView().getmMessageAdapter().notifyDataSetChanged();
//                        requestDataBaseMessagesList();
//                        /**/
//
//                    }
//                }catch (Exception e){
//                    onError(0,e.getStackTrace().toString());
//                }
//
//
//
//            }
//
//            @Override
//            public void onError(int errorCode, String errorMessage) {
//                Application.logEvents(Constants.Urls.SEND_STUDENT_BEACON, "ChatFragment - requestMessagesList  ",errorMessage);
//
////                UtilDialogs.ProcessingDialog.dismiss();
////                UtilityParent.showMessageDialog(getActivity(), getActivity().getString(R.string.error_api), errorMessage);
//                UtilDialogs.showGeneralErrorDialog(getMainActivity()).setDialogeMessage(R.string.api_send_error);
//
//            }
//        });
//
//
//    }
//
//
//    private void parceMessagesToBubbles(ArrayList<MessageHistoryObject> messageHistoryObject) {
//
//        for (int i = 0; i < messageHistoryObject.size(); i++) {
//            String tex = messageHistoryObject.get(i).getMessage();
//            String date_time = messageHistoryObject.get(i).getDateTime();
//
//            if (messageHistoryObject.get(i).getSenderId().intValue() == gcmMessageFromExtra.getMotherId()) {
//                addTextMessage(mother, tex, date_time, false);
//            } else if (messageHistoryObject.get(i).getSenderId().intValue() == gcmMessageFromExtra.getFatherId()) {
//                addTextMessage(father, tex, date_time, false);
//            } else {
//                addTextMessage(teacher, tex, date_time, false);
//            }
//        }
//        mChatView.getMessageView().scrollToEnd();
//
//    }
//
//
//    private void sendMessageToAPI(final SendMessageRequest sendMessageRequest) {
//        /**/
//
//        if (StaticValue.progressDialog != null) {
//            StaticValue.progressDialog.show();
//        }
//
//        /**/
//        ApiController.sendMessage(getActivity(), sendMessageRequest, new OnApiComplete<ArrayList<SendMessageResponse>>() {
//            @Override
//            public void onSuccess(final ArrayList<SendMessageResponse> sendMessageResponseList) {
//
//                boolean recived = false;
//                for (int i = 0; i < sendMessageResponseList.size(); i++) {
//                    if (sendMessageResponseList.get(i).getStatus().equals("ok")) {
//                        recived = true;
//                    }
//                }
//                if (recived) {
//                    String formatedDate = DateTools.Formats.DATE_FORMAT_LOCAL.format(new Date().getTime());
//                    addTextMessage(father, sendMessageRequest.getMessage().getBody(), formatedDate, false);
//                    mChatView.getMessageView().scrollToEnd();
//                    mChatView.setInputText("");
//                    DAO.addMessage(getDatabase(), sendMessageRequest.getSchoolId(), sendMessageRequest.getSender(), formatedDate, sendMessageRequest.getMessageType(), sendMessageRequest.getMessage().getBody(), sendMessageRequest.getSenderId(), gcmMessageFromExtra.getStudentId());
//
//                } else
//                    onError(0, null);
//
//
//                if (StaticValue.progressDialog != null) {
//                    StaticValue.progressDialog.dismiss();
//                }
//            }
//
//            @Override
//            public void onError(int errorCode, String errorMessage) {
////                UtilityParent.showMessageDialog(getActivity(), getActivity().getString(R.string.error_api), errorMessage);
//                UtilDialogs.showGeneralErrorDialog(getMainActivity()).setDialogeMessage(R.string.api_send_error);
//                if (StaticValue.progressDialog != null) {
//                    StaticValue.progressDialog.dismiss();
//                }
//                DAO.addPendingMessage(getDatabase(), new MessageHistoryObject(sendMessageRequest.getSchoolId(), sendMessageRequest.getSender(), null, sendMessageRequest.getMessageType(), sendMessageRequest.getMessage().getBody(), sendMessageRequest.getSenderId()), gcmMessageFromExtra.getStudentId());
//                addTextMessage(father, sendMessageRequest.getMessage().getBody(), DateTools.Formats.DATE_FORMAT_LOCAL.format(new Date().getTime()), true);
//                mChatView.setInputText("");
//
//            }
//        });
//
//    }
//
//
//    public void onSendError(final SendMessageRequest sendMessageRequest) {
//        DAO.addPendingMessage(getDatabase(), new MessageHistoryObject(sendMessageRequest.getSchoolId(), sendMessageRequest.getSender(), null, sendMessageRequest.getMessageType(), sendMessageRequest.getMessage().getBody(), sendMessageRequest.getSenderId()), gcmMessageFromExtra.getStudentId());
//        addTextMessage(teacher, sendMessageRequest.getMessage().getBody(), DateTools.Formats.DATE_FORMAT_LOCAL.format(new Date().getTime()), true);
//        mChatView.setInputText("");
//
//        if (StaticValue.progressDialog != null) {
//            StaticValue.progressDialog.dismiss();
//        }
//
//    }
//
//
//}
