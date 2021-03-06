/*
 * Jitsi, the OpenSource Java VoIP and Instant Messaging client.
 *
 * Distributable under LGPL license. See terms of license at gnu.org.
 */
package net.java.sip.communicator.impl.muc;

import java.util.*;

import net.java.sip.communicator.service.contactsource.*;
import net.java.sip.communicator.service.muc.*;
import net.java.sip.communicator.service.protocol.*;

/**
 * Basic source contact for the chat rooms.
 * 
 * @author Hristo Terezov
 */
public class BaseChatRoomSourceContact
    extends SortedGenericSourceContact
{
    /**
     * The name of the chat room associated with the contact.
     */
    private String chatRoomName;
    
    /**
     * The ID of the chat room associated with the contact.
     */
    private String chatRoomID;
    
    /**
     * The protocol provider of the chat room associated with the contact.
     */
    private ProtocolProviderService provider;
    
    /**
     * Contsructs new chat room source contact.
     * @param chatRoomName the name of the chat room associated with the room.
     * @param chatRoomID the id of the chat room associated with the room.
     * @param query the query associated with the contact.
     * @param pps the protocol provider of the contact.
     * @param isAutoJoin the auto join state.
     */
    public BaseChatRoomSourceContact(String chatRoomName, 
        String chatRoomID, ContactQuery query, ProtocolProviderService pps)
    {
        super(query, query.getContactSource(), chatRoomName,
            generateDefaultContactDetails(chatRoomName));
        
        this.chatRoomName = chatRoomName;
        this.chatRoomID = chatRoomID;
        this.provider = pps;
        
        initContactProperties(ChatRoomPresenceStatus.CHAT_ROOM_OFFLINE);
        
    }
    
    
    /**
     * Sets the given presence status and the name of the chat room associated 
     * with the contact.
     * @param status the presence status to be set.
     */
    protected void initContactProperties(PresenceStatus status)
    {
        setPresenceStatus(status);
        setContactAddress(chatRoomName);
    }
    
    /**
     * Generates the default contact details for 
     * <tt>BaseChatRoomSourceContact</tt> instances.
     * 
     * @param chatRoomName the name of the chat room associated with the contact
     * @return list of default <tt>ContactDetail</tt>s for the contact. 
     */
    private static List<ContactDetail> generateDefaultContactDetails(
        String chatRoomName)
    {
        ContactDetail contactDetail
            = new ContactDetail(chatRoomName);
        List<Class<? extends OperationSet>> supportedOpSets
            = new ArrayList<Class<? extends OperationSet>>();
    
        supportedOpSets.add(OperationSetMultiUserChat.class);
        contactDetail.setSupportedOpSets(supportedOpSets);
    
        List<ContactDetail> contactDetails
            = new ArrayList<ContactDetail>();
    
        contactDetails.add(contactDetail);
        return contactDetails;
    }
    
    /**
     * Returns the id of the chat room associated with the contact.
     * 
     * @return the chat room id.
     */
    public String getChatRoomID()
    {
        return chatRoomID;
    }

    /**
     * Returns the name of the chat room associated with the contact.
     * 
     * @return the chat room name
     */
    public String getChatRoomName()
    {
        return chatRoomName;
    }

    /**
     * Returns the provider of the chat room associated with the contact.
     * 
     * @return the provider
     */
    public ProtocolProviderService getProvider()
    {
        return provider;
    }

    /**
     * Returns the index of this source contact in its parent group.
     *
     * @return the index of this contact in its parent
     */
    @Override
    public int getIndex()
    {
        return -1;
    }
}
